/*
* Copyright (c) 2017 General Electric Company. All rights reserved.
*
* The copyright to the computer software herein is the property of
* General Electric Company. The software may be used and/or copied only
* with the written permission of General Electric Company or in accordance
* with the terms and conditions stipulated in the agreement/contract
* under which the software has been supplied.
* 
* Project Name: nc-mobile-microservice
* File Name: RestTemplateFactory.java
* Date: Mar 23, 2017
* Created By: 212398958
* 
*/
package com.ip.itest.utils.rest;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 212398958
 *
 */
@Repository
@PropertySource("classpath:application.properties")
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

	private static Log LOGGER = LogFactory.getLog(RestTemplateFactory.class);
	private static final String GRANT_TYPE = "?grant_type=password";
	private static final String GRANT_TYPE_PM = "&grant_type=password";
	private static final String GRANT_TYPE_PM_ASSET = "?grant_type=client_credentials";
	private static final String FATAL_ERROR = "FATAL_ERROR";
	private static final String CLIENT_ID = "&client_id=";
	private static final String CLIENT_SECRET = "&client_secret=";
	private static final String C_USERNAME = "&username=";
	private static final String PM_USERNAME = "username=";
	private static final String C_PASSWORD = "&password=";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	private static final String BASIC = "Basic ";
	private static final String NO_CACHE = "no-cache";
	private static final String PREDIX_ZONE_ID = "Predix-Zone-Id";

	@Value("${pm.tokenURL}")
	private String tokenURLPM;
	@Value("${pm.secret}")
	private String secretPM;
	@Value("${pm.devUser}")
	private String devUserPM;
	@Value("${pm.devPass}")
	private String devPassPM;

	private ObjectMapper objectMapper = new ObjectMapper();
	private ResponseHolder<PMOAuthToken, PMError> oAuthTokenPM;
	private ResponseHolder<PMOAuthToken, PMError> oAuthTokenPMAsset;
	private RestTemplate restTemplate;

	/**
	 * Put method for Predix Mobile Service.
	 * 
	 * @param url
	 * @param body
	 * @return
	 */
	public ResponseEntity<String> getForPm(final String url) {
		LOGGER.info("@@@ Entering into RestTemplateFactory:: getForPm()@@@");
		LOGGER.info("@@@ URL ::" + url);
		ResponseEntity<String> response = getRestTemplate().exchange(url, HttpMethod.GET, getEntityForPm(),
				String.class);
		LOGGER.debug("@@@ Staus " + response.getStatusCode());
		// LOGGER.debug("@@@ Body " + response.getBody());
		if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)
				|| response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			LOGGER.info("@@@ Refreshing PM Token @@@");
			refreshPMToken();
			response = getRestTemplate().exchange(url, HttpMethod.GET, getEntityForPm(), String.class);

		}

		// LOGGER.info("@@@ Response ::" + response.getBody());
		return response;
	}

	/**
	 * Put method for Predix Mobile Service.
	 * 
	 * @param url
	 * @param body
	 * @return
	 */
	public ResponseEntity<String> putForPm(final String url, final String body) {
		LOGGER.info("@@@ Entering into RestTemplateFactory:: putForPm()@@@");
		LOGGER.info("@@@ URL ::" + url);
		LOGGER.info("@@@ Body ::" + body);
		ResponseEntity<String> response = getRestTemplate().exchange(url, HttpMethod.PUT, getEntityForPMPut(body),
				String.class);
		LOGGER.debug("@@@ Staus " + response.getStatusCode());
		LOGGER.debug("@@@ Body " + response.getBody());
		if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)
				|| response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			LOGGER.info("@@@ Refreshing PM Token @@@");
			refreshPMToken();
			response = getRestTemplate().exchange(url, HttpMethod.PUT, getEntityForPMPut(body), String.class);

		}

		LOGGER.info("@@@ Response ::" + response.getBody());
		return response;
	}

	/**
	 * Get Images from Predix Mobile Service.
	 * 
	 * @param url
	 * @param body
	 * @return
	 */
	public ResponseEntity<byte[]> getPmImage(final String url) {
		LOGGER.info("@@@ Entering into RestTemplateFactory:: getForPm()@@@");
		LOGGER.info("@@@ URL ::" + url);
		ResponseEntity<byte[]> response = getRestTemplate().exchange(url, HttpMethod.GET,
				new HttpEntity<>(getPMHeadersForImage()), byte[].class);
		if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)
				|| response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			LOGGER.info("@@@ Refreshing PM Token @@@");
			refreshPMToken();
			response = getRestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(getPMHeadersForImage()),
					byte[].class);

		}
		LOGGER.info("@@@ Response ::" + response.getBody());
		return response;
	}

	

	/**
	 * This method will return the UPDATED TOKEN.
	 * 
	 * @return
	 */
	public ResponseHolder<PMOAuthToken, PMError> refreshPMToken() {
		oAuthTokenPM = getPMToken();
		if (oAuthTokenPM.getError() != null) {
			LOGGER.error("@@@ Failed to getToken from PM @@@");
			LOGGER.error(
					oAuthTokenPM.getError().getErrorMessage() + " ### --" + oAuthTokenPM.getError().getErrorCode());
		}
		return oAuthTokenPM;
	}

	/**
	 * This method will get Token for PM.
	 * 
	 * @return
	 */
	private ResponseHolder<PMOAuthToken, PMError> getPMToken() {
		ResponseHolder<PMOAuthToken, PMError> responseHolder = new ResponseHolder<PMOAuthToken, PMError>();
		ResponseEntity<String> response = getRestTemplate().exchange(tokenURLPM, HttpMethod.POST,
				getEntityForPostPMToken(), String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			try {
				responseHolder.setResponse(objectMapper.readValue(response.getBody(), PMOAuthToken.class));
			} catch (JsonParseException e) {
				LOGGER.error("@@@ Failed to JsonParseException @@@");
				LOGGER.error(e.getMessage());
				LOGGER.error(e.getCause());
				responseHolder.setError(new PMError(e.getMessage(), FATAL_ERROR));

			} catch (JsonMappingException e) {
				LOGGER.error("@@@ Failed to JsonMappingException @@@");
				LOGGER.error(e.getMessage());
				LOGGER.error(e.getCause());
				responseHolder.setError(new PMError(e.getMessage(), FATAL_ERROR));
			} catch (IOException e) {
				LOGGER.error("@@@ Failed to IOException @@@");
				LOGGER.error(e.getMessage());
				LOGGER.error(e.getCause());
				responseHolder.setError(new PMError(e.getMessage(), FATAL_ERROR));
			}
		} else {
			responseHolder.setError(new PMError(response.getBody(), response.getStatusCode().toString()));
		}
		return responseHolder;

	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate = new RestTemplate(getclientHttpRequestFactoryProxy());
		// restTemplate = new RestTemplate(requestFactory);
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			/**
			 * Overriding Spring's default error handler.
			 */
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return RestTemplateFactory.isError(response.getStatusCode());
			}

			/**
			 * Overriding Spring's default error handler.
			 */
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				LOGGER.error(
						"@@@ Response error: " + response.getStatusCode() + " : " + response.getStatusText() + "@@@");
			}

		});

		refreshTokenAtPMStart();
		// refreshTokenAtPMAssetStart();

	}

	private RestTemplate getRestTemplate() {
		RestTemplate restTemplate;

		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		// restTemplate = new RestTemplate(requestFactory);

		restTemplate = new RestTemplate(getclientHttpRequestFactoryProxy());
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			/**
			 * Overriding Spring's default error handler.
			 */
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return RestTemplateFactory.isError(response.getStatusCode());
			}

			/**
			 * Overriding Spring's default error handler.
			 */
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				LOGGER.error(
						"@@@ Response error: " + response.getStatusCode() + " : " + response.getStatusText() + "@@@");
			}

		});
		return restTemplate;
	}

	private HttpComponentsClientHttpRequestFactory getclientHttpRequestFactoryProxy() {
		return new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
				.setProxy(new HttpHost("iss-emea-pitc-amsterdamz.proxy.corporate.ge.com", 80, "http")).build());
	}

	/**
	 * This method will be intiated @ application boot-up process. if its not
	 * getting token, it will fail to boot-up.
	 */
	private void refreshTokenAtPMStart() {
		LOGGER.info("@@@ Token Refresh Started for PM @@@");
		oAuthTokenPM = getPMToken();
		if (oAuthTokenPM.getError() != null) {
			LOGGER.error("@@@ Failed to getToken from PM @@@");
			LOGGER.error(
					oAuthTokenPM.getError().getErrorMessage() + " ### --" + oAuthTokenPM.getError().getErrorCode());
			throw new BeanCreationException(
					oAuthTokenPM.getError().getErrorMessage() + " ### --" + oAuthTokenPM.getError().getErrorCode());
		} else {
			LOGGER.info("@@@ Got PM token ==" + oAuthTokenPM.getResponse().getAccessToken() + "@@@");
		}
	}


	public static boolean isError(HttpStatus status) {
		HttpStatus.Series series = status.series();
		return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public RestTemplate getObject() throws Exception {
		// TODO Auto-generated method stub
		return restTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return RestTemplate.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	private HttpEntity<String> getEntityForPostPMToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, BASIC + secretPM);
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		headers.set(HttpHeaders.CACHE_CONTROL, NO_CACHE);
		return new HttpEntity<String>(getBodyForPostPMToken(), headers);
	}

	

	private String getBodyForPostPMToken() {
		return new StringBuffer(PM_USERNAME).append(devUserPM).append(C_PASSWORD).append(devPassPM)
				.append(GRANT_TYPE_PM).toString();
	}

	private HttpEntity<String> getEntityForPMPut(String body) {
		return new HttpEntity<String>(body, getPMHeaders());
	}

	
	private HttpEntity<?> getEntityForPm() {
		return new HttpEntity<>(getPMHeaders());
	}

	/**
	 * Setting headers with OAuth Token.
	 * 
	 * @return
	 */
	private HttpHeaders getPMHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, BEARER + oAuthTokenPM.getResponse().getAccessToken());
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.set(HttpHeaders.CACHE_CONTROL, NO_CACHE);
		return headers;
	}


	/**
	 * Setting headers with OAuth Token.
	 * 
	 * @return
	 */
	private HttpHeaders getPMHeadersForImage() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, BEARER + oAuthTokenPM.getResponse().getAccessToken());
		return headers;
	}

}

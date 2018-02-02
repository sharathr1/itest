package com.ip.itest.predixuaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.itest.predixuaa.model.UserInfo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	@Qualifier("clientCredentialsRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	
	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("#oauth2.hasScope('g01156458')")
	public UserInfo getUserInfo(OAuth2Authentication activeUser) throws Exception {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) activeUser
				.getUserAuthentication();
		

		log.info("TOKEN DETAILS: " + token.getDetails().toString());
		log.info("Authorities: " + activeUser.getUserAuthentication().getDetails());
		log.info("Scopes: " + oAuth2ClientContext.getAccessToken().getScope());
			
		UserInfo user = objectMapper.convertValue(token.getDetails(), UserInfo.class);

		return user;
	}

}

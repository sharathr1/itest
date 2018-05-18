/*
* Copyright (c) 2016 General Electric Company. All rights reserved.
*
* The copyright to the computer software herein is the property of
* General Electric Company. The software may be used and/or copied only
* with the written permission of General Electric Company or in accordance
* with the terms and conditions stipulated in the agreement/contract
* under which the software has been supplied.
* 
* Project Name: gridfit-mobile-microservice
* File Name: PMOAuthToken.java
* Date: Dec 13, 2016
* Created By: 212398958
* 
*/
package com.ip.itest.utils.rest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author 212398958
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "token_type", "refresh_token",
		"expires_in", "scope", "jti" })
public class PMOAuthToken {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("jti")
	private String jti;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public PMOAuthToken() {
	}

	/**
	 * 
	 * @param scope
	 * @param tokenType
	 * @param accessToken
	 * @param expiresIn
	 * @param jti
	 * @param refreshToken
	 */
	public PMOAuthToken(String accessToken, String tokenType,
			String refreshToken, Integer expiresIn, String scope, String jti) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.scope = scope;
		this.jti = jti;
	}

	/**
	 * 
	 * @return The accessToken
	 */
	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * 
	 * @param accessToken
	 *            The access_token
	 */
	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * 
	 * @return The tokenType
	 */
	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * 
	 * @param tokenType
	 *            The token_type
	 */
	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * 
	 * @return The refreshToken
	 */
	@JsonProperty("refresh_token")
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * 
	 * @param refreshToken
	 *            The refresh_token
	 */
	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * 
	 * @return The expiresIn
	 */
	@JsonProperty("expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	/**
	 * 
	 * @param expiresIn
	 *            The expires_in
	 */
	@JsonProperty("expires_in")
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * 
	 * @return The scope
	 */
	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}

	/**
	 * 
	 * @param scope
	 *            The scope
	 */
	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * 
	 * @return The jti
	 */
	@JsonProperty("jti")
	public String getJti() {
		return jti;
	}

	/**
	 * 
	 * @param jti
	 *            The jti
	 */
	@JsonProperty("jti")
	public void setJti(String jti) {
		this.jti = jti;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

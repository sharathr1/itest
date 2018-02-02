package com.ip.itest.predixuaa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2Config {
	
	@Bean
	@ConfigurationProperties("security.oauth2.client")
	public ClientCredentialsResourceDetails oauth2ClientCredentialsResourceDetails() { 
		ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

		return details;
	}

	@Bean
	public OAuth2ClientContext oauth2ClientContext() {
		return new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
	}

	@Bean
	public OAuth2RestTemplate clientCredentialsRestTemplate(OAuth2ClientContext oauth2ClientContext,
			ClientCredentialsResourceDetails details) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(details, oauth2ClientContext);
		return template;
	}
}

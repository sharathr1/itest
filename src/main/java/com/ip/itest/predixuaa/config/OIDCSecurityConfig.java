package com.ip.itest.predixuaa.config;

import javax.servlet.Filter;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ip.itest.predixuaa.properties.GeNetworkProperties;
import com.ip.itest.predixuaa.properties.VcapServicesCredentialsProperties;
import com.ip.itest.predixuaa.security.oauth2.resource.GEUserInfoTokenServices;


@Profile("ge-oidc")
@Configuration
@EnableOAuth2Sso // Protects application with SSO
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class OIDCSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String IDM_GROUP = "g01156458";
	
	@Autowired
	private Environment environment;
	
	@Autowired(required=false)
	private GeNetworkProperties geNetworkSettings;
	
	@Autowired
	private VcapServicesCredentialsProperties vcapProperties;
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;
	
	@Autowired
	private ResourceServerProperties ssoResource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(ArrayUtils.contains(environment.getActiveProfiles(), "local")) {
			System.setProperty("http.proxyHost", geNetworkSettings.getProxyHost());
			System.setProperty("http.proxyPort", geNetworkSettings.getProxyPort());
			System.setProperty("https.proxyHost", geNetworkSettings.getProxyHost());
			System.setProperty("https.proxyPort", geNetworkSettings.getProxyPort());
		}
		
		final String logoutUrl = vcapProperties.getSsoLogoutUrl();
		
		// @formatter:off	
		http
			.headers()
				.httpStrictTransportSecurity()
					.includeSubDomains(true)
					.maxAgeInSeconds(31536000); // 1 YEAR
																									
		http
			.headers()
				.contentSecurityPolicy(
				"default-src 'none'; script-src 'self'; connect-src 'self'; img-src 'self'; style-src 'self'; font-src 'self'");
	
		http	
			.logout()
				.logoutSuccessUrl(logoutUrl)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()			
				.authorizeRequests()
					.antMatchers("/**")
						.hasAuthority(IDM_GROUP)				
						.anyRequest()
						.authenticated()		
			.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
				.addFilterBefore(geSsoFilter(), BasicAuthenticationFilter.class);
		// @formatter:on
	}
	
	/**
	 * GE SSO Filter
	 * 
	 * Used to protect application with IDM Groups
	 * 
	 * @return
	 */
	private Filter geSsoFilter() {
	  OAuth2ClientAuthenticationProcessingFilter ssoFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
	  OAuth2RestTemplate ssoTemplate = new OAuth2RestTemplate(sso(), oauth2ClientContext);
	  
	  ssoFilter.setRestTemplate(ssoTemplate);
	  ssoFilter.setTokenServices(new GEUserInfoTokenServices(ssoResource.getUserInfoUri(), sso().getClientId()));
	 
	  return ssoFilter;
	}
	
	@Bean
	@ConfigurationProperties("security.oauth2.client")
	public AuthorizationCodeResourceDetails sso() {
		return new AuthorizationCodeResourceDetails();
	}
}

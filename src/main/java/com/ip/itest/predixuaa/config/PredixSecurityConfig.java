/**
 * 
 */
package com.ip.itest.predixuaa.config;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ip.itest.predixuaa.properties.GeNetworkProperties;
import com.ip.itest.predixuaa.properties.VcapServicesCredentialsProperties;

/**
 * OAuth2SecurityConfig
 * 
 * @author "Andy Wickersham (212425740)"
 *
 */
@Profile("predix")
@Configuration
@EnableOAuth2Sso // Protects application with SSO
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class PredixSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;

	@Autowired(required = false)
	private GeNetworkProperties geNetworkSettings;
	
	@Autowired
	private VcapServicesCredentialsProperties vcapProperties;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		if (ArrayUtils.contains(environment.getActiveProfiles(), "local")) {
			System.setProperty("http.proxyHost", geNetworkSettings.getProxyHost());
			System.setProperty("http.proxyPort", geNetworkSettings.getProxyPort());
			System.setProperty("https.proxyHost", geNetworkSettings.getProxyHost());
			System.setProperty("https.proxyPort", geNetworkSettings.getProxyPort());
		}

		final String logoutUrl = vcapProperties.getUaaUrl() + "/logout?&redirect=" + vcapProperties.getSsoLogoutUrl();

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
			.cors()
				.and() // by default uses a Bean by the name of corsConfigurationSource
				.logout()
					.logoutSuccessUrl(logoutUrl)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and()
				.antMatcher("/**")
					.authorizeRequests().anyRequest().authenticated()
					.and()
				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	   // http.csrf().disable();

		// @formatter:on
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
				"/swagger-ui.html", "/swagger-resources/**","/webjars/**");
	}
	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//	}

}

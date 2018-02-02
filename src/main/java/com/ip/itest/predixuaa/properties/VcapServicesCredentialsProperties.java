/**
 * 
 */
package com.ip.itest.predixuaa.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 
 * @author Andy Wickersham (212425740)
 *
 */
@Configuration //tells Spring to treat this as a configuration class and register it as a Bean
@EnableConfigurationProperties //tells Spring to treat this class as a consumer of application.yml/properties values
@ConfigurationProperties(prefix="vcap.services.predix-shared-uaa-spring-boot-web-cups.credentials") //tells Spring what section this class represents
@Data
public class VcapServicesCredentialsProperties {
	private String oidcClientId;
	private String oidcClientSecret;
	private String oidcUrl;
	private String oidcScope;
	
	private String uaaClientId;
	private String uaaClientSecret;
	private String uaaUrl;
	private String uaaScope;
	
	private String ssoLogoutUrl;
	
}

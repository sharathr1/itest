/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * http://javasampleapproach.com/spring-framework/spring-bean-scope-using-annotation-singleton-prototype-request-session-global-session-application
 * @author 999951
 *
 */
@RestController
public class ScopeWebController {
	@Autowired
	private Customer customer;

	@GetMapping("/name")//Singleton
	public String name() {
		String result = customer.getCustomerName();
		customer.setCustomerName("Adam", "Johnson");
		return "init Data: " + result + "|-----| modified Data: " + customer.getCustomerName();
	}

	@GetMapping("/namecheck")
	public String namecheck() {
		return "check Data: " + customer.getCustomerName();
	}

	@GetMapping("/language")//Prototype
	public String language() {
		String result = customer.getCustomerLanguage();
		//customer.setCustomerLanguage("French") //this command creates new instance of Language
		return "init Data: " + result + "|-----| modified Data: " + customer.setCustomerLanguage("French");
	}

	@GetMapping("/languagecheck")
	public String languagecheck() {
		return "check Data: " + customer.getCustomerLanguage();
	}

	@GetMapping("/address")//Request
	public String address() {
		String result = customer.getCustomerAddress();
		customer.setCustomerAddress("EU");
		return "init Data: " + result + "|-----| modified Data: " + customer.getCustomerAddress();
	}

	@GetMapping("/addresscheck")
	public String addresscheck() {
		return "check Data: " + customer.getCustomerAddress();
	}
	
	@GetMapping("/age")//Session = 10ms
	public String age() {
		String result = customer.getCustomerAge();
		customer.setCustomerAge("25");
		return "init Data: " + result + "|-----| modified Data: " + customer.getCustomerAge();
	}

	@GetMapping("/agecheck")
	public String agecheck() {
		return "check Data: " + customer.getCustomerAge();
	}
}
/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class RequestAddres {
	private String address = "US";
 
	public RequestAddres() {
		System.out.println("Create new Address: " + this.address);
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
}
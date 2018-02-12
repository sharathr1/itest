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

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionAge {
	private String age = "24";

	public SessionAge() {
		System.out.println("Create new Age: " + this.age);
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
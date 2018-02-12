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

@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PrototypeBeanLang {
	private String language = "English";

	public PrototypeBeanLang() {
		System.out.println("Create new Language: " + this.language);
	}

	public String getLanguage() {
		return language;
	}

	public String setLanguage(String language) {
		this.language = language;
		return this.language;
	}
}

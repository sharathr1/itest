/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class SingletonBeanName {
	private String firstName = "Jack";
	private String lastName = "Smith";

	public SingletonBeanName() {
		System.out.println("Create new Name: " + this.toString());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

}

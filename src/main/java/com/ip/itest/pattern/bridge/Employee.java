/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.bridge;

public class Employee implements Organization {
	
	@Override
	public void save() {
		System.out.println("Employee");
	}

}

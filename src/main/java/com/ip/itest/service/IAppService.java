package com.ip.itest.service;

import java.util.List;

import com.ip.itest.common.domain.Employee;

public interface IAppService {

	public List<Employee> getAllEmloyee();

	public default void getLog() {
		System.out.println("Interface");
	}

	public static void getStaticLog() {
		System.out.println("Static Interface");
	}

}

package com.ip.itest.java;

import org.springframework.data.repository.CrudRepository;

import com.ip.itest.common.domain.Employee;

public class MyGen<T> {
	T obj;

	void add(T obj) {
		this.obj = obj;
	}

	T get() {
		return obj;
	}
}
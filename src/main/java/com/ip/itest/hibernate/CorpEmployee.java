/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeehib")
public class CorpEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	public CorpEmployee() {
	}

	public CorpEmployee(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public CorpEmployee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Employee: " + this.id + ", " + this.name + ", " + this.age; 
	}
	
}
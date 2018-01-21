package com.ip.itest.dal;

import org.springframework.data.repository.CrudRepository;

import com.ip.itest.common.domain.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
	 
}
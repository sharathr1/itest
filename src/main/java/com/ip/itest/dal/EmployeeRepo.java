/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.dal;

import org.springframework.data.repository.CrudRepository;

import com.ip.itest.common.domain.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
	 
}
/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ip.itest.common.domain.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
	 
}
/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.daimond;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ip.itest.common.domain.Employee;

@Service
@Transactional
public class UserServiceImpl implements IUserService, IUserService2 {

	@Override
	public Employee getUsersDetails() {
		return null;
	}

	@Override
	public List<Employee> postUsersDetails(List<Employee> user) {
		// TODO Auto-generated method stub
		return null;
	}

}

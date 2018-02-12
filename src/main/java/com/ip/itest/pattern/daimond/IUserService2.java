/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.daimond;

import java.util.List;

import com.ip.itest.common.domain.Employee;

public interface IUserService2 {

	Employee getUsersDetails();

	List<Employee> postUsersDetails(List<Employee> user);

}

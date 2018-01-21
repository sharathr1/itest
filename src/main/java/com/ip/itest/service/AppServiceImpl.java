package com.ip.itest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ip.itest.common.domain.Employee;

@Service
public class AppServiceImpl implements IAppService {

	@Override
	public List<Employee> getAllEmloyee() {
		List<Employee> eList = new ArrayList();
		Employee e= new Employee();
	e.setE_id("1");
	eList.add(e);
		return eList ;
	}

	
}

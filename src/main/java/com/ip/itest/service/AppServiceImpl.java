package com.ip.itest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.itest.common.domain.Employee;
import com.ip.itest.dal.EmployeeRepo;

@Service
public class AppServiceImpl implements IAppService {

	@Autowired
	private EmployeeRepo empRepo;

	@Override
	public List<Employee> getAllEmloyee() {
		// List<Employee> eList = new ArrayList();
		// Employee e = new Employee();
		// e.setE_id("1");
		// eList.add(e);
		System.out.println("DB "+empRepo.findAll());
		return (List<Employee>) empRepo.findAll();
	}

	@Override
	public List<Employee> postEmployees(List<Employee> empList) {
		empRepo.save(empList);
		return empList;
	}

}

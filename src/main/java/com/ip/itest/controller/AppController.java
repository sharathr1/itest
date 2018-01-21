package com.ip.itest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ip.itest.common.domain.Employee;
import com.ip.itest.service.IAppService;

@RestController
public class AppController {

	@Autowired
	private IAppService appService;
	@GetMapping("/test")
	public List<Employee> getAllEmployees(){
		return appService.getAllEmloyee();
	}
	@PostMapping("/emp")
	public List<Employee> postEmployees(@RequestBody List<Employee> empList){
		return appService.postEmployees(empList);
	}
}

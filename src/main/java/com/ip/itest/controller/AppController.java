package com.ip.itest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ip.itest.common.domain.Employee;
import com.ip.itest.service.IAppService;

import io.swagger.annotations.ApiOperation;

@RestController
public class AppController {

	@Autowired
	private IAppService appService;

	/**
	 * @Cacheable will skip running the method, whereas @CachePut will actually
	 *            run the method and then put its results in the cache.
	 * @CacheEvict annotation is used to indicate the removal of one or more/all
	 *             values
	 * 
	 *             Group multiple caching annotations with @Caching
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@Cacheable(value = "employee")
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(@RequestParam String id, @RequestParam String name) {
		return appService.getAllEmloyee();
	}

	@PostMapping("/employee")
	public List<Employee> postEmployees(@RequestBody List<Employee> empList) {
		return appService.postEmployees(empList);
	}

	@GetMapping("/employeerefresh")	
	@CacheEvict(value = "employee", allEntries = true) // Will clear cache of employee
	public List<Employee> getAllEmloyeeRefresh(@RequestParam String id, @RequestParam String name) {
		return appService.getAllEmloyee();
	}

	/**
	 * Hashcode - same object reference returns same hashcode : e1 = e3 equals hashcode
	 * Two new object can't be same hashcode but can be equal by overriding methods
	 * 
	 * @return
	 */
	@GetMapping("/hashcodeandequals")
	public Employee getAllEmployees1() {
		Employee e1 = new Employee();
		e1.setEId("2");
		Employee e2 = new Employee();
		e2.setEId("2");

		Employee e3 = e1;
		// same reference same hashcode

		System.out.println(e1.hashCode());
		System.out.println(e2.hashCode());
		System.out.println(e3.hashCode());

		if (e1 == e3) {
			// with / without overriding Hashcode & equals method
			System.err.println("clone ==");
		}
		if (e1 == e2) {
			// Can't be same
			System.err.println("==");
		}
		if (e1.equals(e2)) {
			// after overriding Hashcode & equals method
			System.err.println("equals()");
		}

		return Employee.builder().eId("1").eName("Sharath").build();
	}

	@GetMapping("/test/dev")
	@ApiOperation(value = "Future Production KPI Details", notes = "")
	@ResponseBody
	public String triggerAlertA(@PathVariable(value = "orgid", required = true) Integer orgId,
			@RequestParam(value = "url", defaultValue = "0", required = false) String url)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		// request.setConfig(getProxy());
		HttpResponse response = httpClient.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url + " https://sqm.gs.ec.ge.com");
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		return result.toString();
	}

	public RequestConfig getProxy() {
		HttpHost proxy = new HttpHost("http-proxy.em.health.ge.com", 88, "https");
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		return config;
	}
}

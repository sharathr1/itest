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
	@GetMapping("/test")
	public List<Employee> getAllEmployees(){
		return appService.getAllEmloyee();
	}
	@PostMapping("/emp")
	public List<Employee> postEmployees(@RequestBody List<Employee> empList){
		return appService.postEmployees(empList);
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

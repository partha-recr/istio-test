package com.partha.customer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class CustomerController {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${version}")
	private String version;
	
	@Value("${account.endpoint}")
	private String url;
	
	@Value("${account.endpoint.route}")
	private String routeurl;

	@GetMapping("getcustomer")
	public String getCustomer(@RequestHeader(value = "Authorization", required = false) String authorization ,@RequestHeader(value = "end-user", required = false) String endUser) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorization);
		headers.set("end-user", endUser);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		System.out.println("Authorization :" + authorization);
		ResponseEntity<String> respEntity = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
		//ResponseEntity<String> respEntity = restTemplate.exchange("http://localhost:8081/getaccount",HttpMethod.GET, entity, String.class);
		return "--From Customer---version:" +version +" End user:"+endUser+" data: " + respEntity.getBody();
	}
	
	@GetMapping("getroute")
	public String getroute(@RequestHeader(value = "Authorization", required = false) String authorization) {
		System.out.println("Authorization value is : " + authorization);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorization);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(routeurl,HttpMethod.GET, entity, String.class);
		return respEntity.getBody();
	}

}

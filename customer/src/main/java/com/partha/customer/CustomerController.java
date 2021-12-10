package com.partha.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer/")
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
	public String getCustomer(@RequestHeader HttpHeaders headers) {
		System.out.println("Authorization :" + headers.get("Authorization") + " End user:" + headers.get("end-user"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return "--From Customer---version:" + version + " End user:" + headers.get("end-user") + " data: "
				+ respEntity.getBody();
	}

	@GetMapping("getroute")
	public String getroute(@RequestHeader HttpHeaders headers) {
		System.out.println("Authorization :" + headers.get("Authorization") + " End user:" + headers.get("end-user"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(routeurl, HttpMethod.GET, entity, String.class);
		return respEntity.getBody();
	}

}

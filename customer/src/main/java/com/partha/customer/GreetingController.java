package com.partha.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer/")
public class GreetingController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${account.endpoint.greeturl}")
	private String greeturl;
	
	@Value("${version}")
	private String version;
	

	@GetMapping("greet")
	public String getGreet() {
		ResponseEntity<String> respEntity = restTemplate.getForEntity(greeturl,String.class);
		return "--From Customer-Greet--version:" + version+"-----"+respEntity.getBody();
	}

}

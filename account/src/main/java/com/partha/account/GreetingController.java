package com.partha.account;

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
@RequestMapping("/")
public class GreetingController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${payment.endpoint.greeturl}")
	private String greeturl;
	
	@Value("${version}")
	private String version;
	

	@GetMapping("greet")
	public String getGreet(@RequestHeader HttpHeaders headers) {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(greeturl, HttpMethod.GET, entity, String.class);
		return "--From Account-Greet--version:" + version+"  -- "+respEntity.getBody();
	}

}

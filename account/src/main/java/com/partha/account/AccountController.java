package com.partha.account;

import java.util.ArrayList;
import java.util.List;

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
public class AccountController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${version}")
	private String version;

	@Value("${payment.endpoint}")
	private String url;

	@Value("${payment.endpoint.route}")
	private String routeurl;

	@GetMapping("getaccount")
	public String getAccount(@RequestHeader HttpHeaders headers) {
		System.out.println("Authorization :" + headers.get("Authorization") + " End user:" + headers.get("end-user"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return "--From Account-version:" + version + " EndUser:" + headers.get("end-user") + " data:" + respEntity.getBody();
	}

	@GetMapping("getroute")
	public String getroute(@RequestHeader HttpHeaders headers) throws Exception {
		System.out.println("Authorization :" + headers.get("Authorization") + " End user:" + headers.get("end-user"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		List<String> arr = new ArrayList();
		for (int i = 1; i <= 100; i++) {
			ResponseEntity<String> respEntity = restTemplate.exchange(routeurl, HttpMethod.GET, entity, String.class);
			arr.add(respEntity.getBody());
			// Thread.sleep(1000);
		}
		String val = String.join("--", arr);
		return val;
	}
}

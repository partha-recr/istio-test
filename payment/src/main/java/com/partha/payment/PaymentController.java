package com.partha.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PaymentController {
	
	@Value("${version}")
	private String version;

	@GetMapping("getpayment")
	public String getPayment(@RequestHeader HttpHeaders headers) {
		System.out.println("Authorization value is : " + headers.get("Authorization"));
		return "--From Payment-Version:" +version+" EndUser:"+headers.get("end-user");
	}
	
	@GetMapping("getroute")
	public String getroute(@RequestHeader HttpHeaders headers) {
		System.out.println("Authorization value is : " + headers.get("Authorization"));
		return version;
	}
}

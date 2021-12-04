package com.partha.payment;

import org.springframework.beans.factory.annotation.Value;
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
	public String getPayment(@RequestHeader(value = "Authorization", required = false) String authorization) {
		System.out.println("Authorization value is : " + authorization);
		return "--From Payment with Version:" +version;
	}
}

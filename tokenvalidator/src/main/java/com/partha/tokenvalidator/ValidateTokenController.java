package com.partha.tokenvalidator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateTokenController {

    @GetMapping("/isvalid")
    public ResponseEntity<String> getSecret() {
        return ResponseEntity.ok("You are here..all good!");
    }

}

package com.partha.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.partha.authserver.jwt.CreateJwtApiResponse;
import com.partha.authserver.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtController {

	@Autowired
    private JwtService jwtService;

    @GetMapping("/jwt/{name}")
    public ResponseEntity getJwt(@PathVariable String name) {
        if("partha".equals(name)) {
            return ResponseEntity.ok(
                CreateJwtApiResponse.builder()
                    .token(jwtService.generateToken(name))
                    .build()
            );
        } else {
            return ResponseEntity.ok("Sorry you are not 'The detonator'");
        }

    }
}

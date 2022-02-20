package com.partha.authserver.jwt;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.partha.authserver.JwtConfig;
import com.partha.authserver.util.DateUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {

	@Autowired
    private  JwtConfig jwtConfig;
	@Autowired
    private  JwtKeyProvider jwtKeyProvider;
	@Autowired
    private  DateUtil dateUtil;

    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusMinutes(jwtConfig.getExpirationInMinutes());
 
        return Jwts.builder()
            .setExpiration(dateUtil.toDate(expiryDate))
            .signWith(SignatureAlgorithm.RS256, jwtKeyProvider.getPrivateKey())
            .claim("username", username)
            .compact();
    }
}

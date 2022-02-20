package com.partha.tokenvalidator.jwt;

public class JwtInitializationException extends RuntimeException {
    public JwtInitializationException(Throwable e) {
        super("Something went wong while reading public key!", e);
    }
}

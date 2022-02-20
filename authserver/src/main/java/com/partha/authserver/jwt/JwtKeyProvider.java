package com.partha.authserver.jwt;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.partha.authserver.util.Base64Util;
import com.partha.authserver.util.ResourceUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtKeyProvider {

	@Autowired
    private  ResourceUtil resourceUtil;
	@Autowired
    private  Base64Util base64Util;

    @Getter
    private PrivateKey privateKey;

    @PostConstruct
    public void init() {
        privateKey = readKey(
            "classpath:certs/rsa-key.pkcs8.private",
            "PRIVATE",
            this::privateKeySpec,
            this::privateKeyGenerator
        );
    }

    private <T extends Key> T readKey(String resourcePath, String headerSpec, Function<String, EncodedKeySpec> keySpec, BiFunction<KeyFactory, EncodedKeySpec, T> keyGenerator) {
        try {
            String keyString = resourceUtil.asString(resourcePath);
            //TODO you can check the headers and throw an exception here if you want

            keyString = keyString
                .replace("-----BEGIN " + headerSpec + " KEY-----", "")
                .replace("-----END " + headerSpec + " KEY-----", "")
                .replaceAll("\\s+", "");

            return keyGenerator.apply(KeyFactory.getInstance("RSA"), keySpec.apply(keyString));
        } catch(NoSuchAlgorithmException | IOException e) {
            throw new JwtInitializationException(e);
        }
    }

    private EncodedKeySpec privateKeySpec(String data) {
        return new PKCS8EncodedKeySpec(base64Util.decode(data));
    }

    private PrivateKey privateKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePrivate(spec);
        } catch(InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }

}


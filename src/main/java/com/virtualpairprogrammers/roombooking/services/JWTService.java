package com.virtualpairprogrammers.roombooking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Service
public class JWTService {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private long expirationTime = 1800000;

    @PostConstruct
    private void initKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public String generateToken(String name, String role) {
        return JWT.create()
                .withClaim("user", name)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }
}

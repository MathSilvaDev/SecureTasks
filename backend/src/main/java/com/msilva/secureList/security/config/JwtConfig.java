package com.msilva.secureList.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    @Value("${spring.security.jwt.public-key}")
    private RSAPublicKey publicKey;

    @Value("${spring.security.jwt.private-key}")
    private RSAPrivateKey privateKey;
}

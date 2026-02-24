package com.msilva.secureList.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    @Value("${}")
    private RSAPublicKey publicKey;

    @Value("${}")
    private RSAPrivateKey privateKey;
}

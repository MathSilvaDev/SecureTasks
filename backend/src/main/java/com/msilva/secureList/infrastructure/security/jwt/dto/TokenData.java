package com.msilva.secureList.infrastructure.security.jwt.dto;

public record TokenData(
        String token,
        Long expiresAt
) { }

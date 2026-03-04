package com.msilva.secureList.security.jwt.dto;

public record TokenData(
        String token,
        Long expiresAt
) { }

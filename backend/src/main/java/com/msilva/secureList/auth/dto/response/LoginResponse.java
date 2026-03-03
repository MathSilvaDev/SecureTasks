package com.msilva.secureList.auth.dto.response;

public record LoginResponse(
        String token,
        Long expiresAt
) { }

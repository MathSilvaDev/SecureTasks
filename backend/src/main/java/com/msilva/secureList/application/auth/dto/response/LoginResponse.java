package com.msilva.secureList.application.auth.dto.response;

public record LoginResponse(
        String token,
        Long expiresAt
) { }

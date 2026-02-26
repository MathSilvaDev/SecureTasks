package com.msilva.secureList.auth.dto.response;

public record LoginResponse(
        String Token,
        Long expiresAt
) { }

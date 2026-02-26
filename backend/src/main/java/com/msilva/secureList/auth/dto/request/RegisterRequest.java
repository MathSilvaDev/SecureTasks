package com.msilva.secureList.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(max = 20)
        String username,

        @NotBlank
        @Size(min = 8)
        String password

) { }

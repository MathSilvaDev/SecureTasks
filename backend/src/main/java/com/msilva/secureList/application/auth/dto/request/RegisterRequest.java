package com.msilva.secureList.application.auth.dto.request;

import com.msilva.secureList.common.validation.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 3, max = ValidationConstants.USERNAME_MAX_LENGTH)
        String username,

        @NotBlank
        @Size(min = ValidationConstants.PASSWORD_MIN_LENGTH)
        String password

) { }

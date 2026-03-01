package com.msilva.secureList.task.dto.request;

import com.msilva.secureList.common.validation.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank
        @Size(min = 3, max = ValidationConstants.TITLE_MAX_LENGTH)
        String title,

        @NotBlank
        @Size(min = 3, max = ValidationConstants.DESCRIPTION_MAX_LENGTH)
        String description
) { }

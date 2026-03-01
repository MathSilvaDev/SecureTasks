package com.msilva.secureList.task.dto.response;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        boolean isCompleted,
        LocalDate createdAt
) {}

package com.example.simplesaga.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryCreatingRequest(
        @NotEmpty
        @Size(max = 255)
        String title
) {
}

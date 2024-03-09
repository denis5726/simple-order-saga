package com.example.simplesaga.order.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record ProductCreatingRequest(
        @NotEmpty
        @Size(max = 255)
        String title,
        String description,
        @NotNull
        @Min(0)
        @Max(1_000_000_000)
        BigDecimal price,
        @NotNull
        Currency currency,
        @NotEmpty
        List<UUID> categoryIds
) {
}

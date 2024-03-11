package com.example.simplesaga.delivery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record WarehouseCreatingRequest(
        @NotNull
        @Size(max = 255)
        String cityName,
        @NotNull
        @Size(max = 255)
        String streetName,
        @NotNull
        @Size(max = 36)
        String home,
        @NotNull
        @Min(0)
        Long capacity
) {
}

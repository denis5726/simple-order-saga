package com.example.simplesaga.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderCreatingRequest(
        @NotEmpty
        List<Entry> entries
) {
    public record Entry(
            @NotNull
            UUID product,
            @NotNull
            @Min(1)
            Integer amount
    ) {
    }
}

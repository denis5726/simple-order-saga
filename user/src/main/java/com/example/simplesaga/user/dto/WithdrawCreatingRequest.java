package com.example.simplesaga.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record WithdrawCreatingRequest(
        @NotNull
        UUID accountId,
        @NotNull
        @Positive
        BigDecimal amount
) {
}

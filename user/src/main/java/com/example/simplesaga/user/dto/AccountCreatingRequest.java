package com.example.simplesaga.user.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Currency;

public record AccountCreatingRequest(
        @NotNull
        Currency currency
) {
}

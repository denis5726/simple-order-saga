package com.example.simplesaga.user.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record AccountDto(
        UUID id,
        BigDecimal amount,
        Currency currency
) {
}

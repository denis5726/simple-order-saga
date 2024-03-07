package com.example.simplesaga.order.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        String state,
        UUID userId,
        List<Entry> entries
) {
    public record Entry(
            UUID id,
            UUID productId,
            BigDecimal price,
            Currency currency,
            Integer amount
    ) {
    }
}

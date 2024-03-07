package com.example.simplesaga.order.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record ProductCreatingRequest(
        String title,
        String description,
        BigDecimal price,
        Currency currency,
        List<UUID> categoryIds
) {
}

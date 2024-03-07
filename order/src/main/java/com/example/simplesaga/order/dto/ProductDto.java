package com.example.simplesaga.order.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record ProductDto(
        UUID id,
        String title,
        String description,
        BigDecimal price,
        Currency currency,
        List<CategoryDto> categories
) {
}

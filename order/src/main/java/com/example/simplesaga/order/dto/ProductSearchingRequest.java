package com.example.simplesaga.order.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;

public record ProductSearchingRequest(
        UUID id,
        String title,
        String description,
        BigDecimal priceLowerBound,
        BigDecimal priceUpperBound,
        Currency currency,
        Map<Field, Direction> sort,
        Integer page
) {
    public enum Field {
        TITLE, PRICE
    }

    public enum Direction {
        ASC, DESC
    }
}

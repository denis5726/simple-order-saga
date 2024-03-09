package com.example.simplesaga.order.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record ProductSearchingRequest(
        UUID id,
        String title,
        String description,
        BigDecimal priceLowerBound,
        BigDecimal priceUpperBound,
        Currency currency,
        List<SortOrder> sort,
        Integer page
) {
    public record SortOrder(
            Field field, Direction direction
    ) {

    }

    public enum Field {
        TITLE, PRICE
    }

    public enum Direction {
        ASC, DESC
    }
}

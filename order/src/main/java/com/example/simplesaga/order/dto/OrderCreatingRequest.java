package com.example.simplesaga.order.dto;

import java.util.List;
import java.util.UUID;

public record OrderCreatingRequest(
        List<Entry> entries
) {
    public record Entry(
        UUID product,
        Integer amount
    ) {
    }
}

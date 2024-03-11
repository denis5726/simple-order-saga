package com.example.simplesaga.delivery.dto;

import java.util.UUID;

public record WarehouseItemDto(
        UUID id,
        UUID warehouseId,
        UUID productId,
        Integer amount,
        Integer size
) {
}

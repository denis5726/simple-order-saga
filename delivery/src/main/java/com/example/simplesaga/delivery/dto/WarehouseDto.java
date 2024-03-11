package com.example.simplesaga.delivery.dto;

import java.util.UUID;

public record WarehouseDto(
        UUID id,
        String city,
        String street,
        String home
) {
}

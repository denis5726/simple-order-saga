package com.example.simplesaga.delivery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WarehouseItemCreatingRequest(
        @NotNull
        UUID warehouseId,
        @NotNull
        UUID productId,
        @NotNull
        @Min(1)
        Integer amount,
        @NotNull
        @Min(1) // TODO Добавить связку товар-размер (вообще подумать как хранить вместимость). Пока ограничение на уровне приложения
        Integer size
) {
}

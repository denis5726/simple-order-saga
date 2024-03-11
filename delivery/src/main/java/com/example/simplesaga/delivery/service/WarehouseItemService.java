package com.example.simplesaga.delivery.service;

import com.example.simplesaga.delivery.dto.WarehouseItemCreatingRequest;
import com.example.simplesaga.delivery.dto.WarehouseItemDto;

import java.util.List;
import java.util.UUID;

public interface WarehouseItemService {

    WarehouseItemDto findById(UUID id);

    List<WarehouseItemDto> findByWarehouse(UUID id);

    WarehouseItemDto create(WarehouseItemCreatingRequest request);

    WarehouseItemDto move(UUID itemId, UUID warehouseToId);
}

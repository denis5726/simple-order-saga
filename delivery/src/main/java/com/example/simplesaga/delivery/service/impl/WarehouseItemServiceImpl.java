package com.example.simplesaga.delivery.service.impl;

import com.example.simplesaga.delivery.dto.WarehouseItemCreatingRequest;
import com.example.simplesaga.delivery.dto.WarehouseItemDto;
import com.example.simplesaga.delivery.service.WarehouseItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseItemServiceImpl implements WarehouseItemService {

    @Override
    public WarehouseItemDto findById(UUID id) {
        return null;
    }

    @Override
    public List<WarehouseItemDto> findByWarehouse(UUID id) {
        return null;
    }

    @Override
    public WarehouseItemDto create(WarehouseItemCreatingRequest request) {
        return null;
    }

    @Override
    public WarehouseItemDto move(UUID itemId, UUID warehouseToId) {
        return null;
    }
}

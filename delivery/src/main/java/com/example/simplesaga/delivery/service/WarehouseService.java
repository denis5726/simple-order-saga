package com.example.simplesaga.delivery.service;

import com.example.simplesaga.delivery.dto.WarehouseCreatingRequest;
import com.example.simplesaga.delivery.dto.WarehouseDto;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {

    WarehouseDto findById(UUID id);

    List<WarehouseDto> findByCity(String cityName);

    WarehouseDto create(WarehouseCreatingRequest request);
}

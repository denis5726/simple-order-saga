package com.example.simplesaga.delivery.service.impl;

import com.example.simplesaga.delivery.dto.WarehouseCreatingRequest;
import com.example.simplesaga.delivery.dto.WarehouseDto;
import com.example.simplesaga.delivery.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public WarehouseDto findById(UUID id) {
        return null;
    }

    @Override
    public List<WarehouseDto> findByCity(String cityName) {
        return null;
    }

    @Override
    public WarehouseDto create(WarehouseCreatingRequest request) {
        return null;
    }
}

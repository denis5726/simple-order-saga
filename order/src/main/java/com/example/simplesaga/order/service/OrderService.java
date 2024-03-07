package com.example.simplesaga.order.service;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.order.dto.OrderCreatingRequest;
import com.example.simplesaga.order.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDto findById(UUID id, JwtAuthentication jwtAuthentication);

    List<OrderDto> findAll(LocalDateTime createdAtLowerBound, JwtAuthentication jwtAuthentication);

    OrderDto create(OrderCreatingRequest request, JwtAuthentication jwtAuthentication);

    void delete(UUID id, JwtAuthentication jwtAuthentication);
}

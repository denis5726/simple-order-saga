package com.example.simplesaga.order.service;

import com.example.simplesaga.order.dto.ProductCreatingRequest;
import com.example.simplesaga.order.dto.ProductDto;
import com.example.simplesaga.order.dto.ProductSearchingRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> findByFilters(ProductSearchingRequest request);

    ProductDto create(ProductCreatingRequest request);

    ProductDto update(UUID id, ProductCreatingRequest request);

    void delete(UUID id);
}

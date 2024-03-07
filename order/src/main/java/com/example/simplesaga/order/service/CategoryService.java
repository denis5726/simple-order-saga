package com.example.simplesaga.order.service;

import com.example.simplesaga.order.dto.CategoryCreatingRequest;
import com.example.simplesaga.order.dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDto findById(UUID id);

    CategoryDto findByTitle(String title);

    List<CategoryDto> findAll();

    CategoryDto create(CategoryCreatingRequest request);

    CategoryDto update(UUID id, String title);

    void delete(UUID id);
}

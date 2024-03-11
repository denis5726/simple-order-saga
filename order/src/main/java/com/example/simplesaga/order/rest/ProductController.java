package com.example.simplesaga.order.rest;

import com.example.simplesaga.order.dto.ProductCreatingRequest;
import com.example.simplesaga.order.dto.ProductDto;
import com.example.simplesaga.order.dto.ProductPageDto;
import com.example.simplesaga.order.dto.ProductSearchingRequest;
import com.example.simplesaga.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/search")
    public ProductPageDto findByFilters(@RequestBody ProductSearchingRequest request) {
        return productService.findByFilters(request);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductCreatingRequest request) {
        return productService.create(request);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable UUID id, @RequestBody ProductCreatingRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }
}

package com.example.simplesaga.order.service;

import com.example.simplesaga.order.dto.ProductSearchingRequest;
import com.example.simplesaga.order.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface ProductFilterBuilder {

    Specification<Product> buildFilters(ProductSearchingRequest request);

    PageRequest buildPage(ProductSearchingRequest request);
}

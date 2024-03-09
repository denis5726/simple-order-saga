package com.example.simplesaga.order.service.impl;

import com.example.simplesaga.order.dto.ProductSearchingRequest;
import com.example.simplesaga.order.entity.Product;
import com.example.simplesaga.order.entity.Product_;
import com.example.simplesaga.order.service.ProductFilterBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ProductFilterBuilderImpl implements ProductFilterBuilder {
    private static final int PAGE_SIZE = 50;
    private static final Map<ProductSearchingRequest.Field, String> FIELD_STRING_MAP = new EnumMap<>(Map.of(
            ProductSearchingRequest.Field.TITLE, Product_.TITLE,
            ProductSearchingRequest.Field.PRICE, Product_.PRICE
    ));
    private static final Map<ProductSearchingRequest.Direction, Sort.Direction> DIRECTION_MAP = new EnumMap<>(Map.of(
            ProductSearchingRequest.Direction.ASC, Sort.Direction.ASC,
            ProductSearchingRequest.Direction.DESC, Sort.Direction.DESC
    ));
    private static final Sort.Order DEFAULT_ORDER = new Sort.Order(Sort.Direction.DESC, Product_.CREATED_AT);

    @Override
    public Specification<Product> buildFilters(ProductSearchingRequest request) {
        return (root, query, cb) ->
                cb.and(
                        Objects.nonNull(request.id()) ? cb.equal(root.get(Product_.ID), request.id()) : cb.and(),
                        Objects.nonNull(request.title()) ?
                                cb.like(root.get(Product_.TITLE), containsPattern(request.title()))
                                : cb.and(),
                        Objects.nonNull(request.description()) ?
                                cb.like(root.get(Product_.DESCRIPTION), containsPattern(request.description()))
                                : cb.and(),
                        Objects.nonNull(request.priceLowerBound()) ?
                                cb.greaterThanOrEqualTo(root.get(Product_.PRICE), request.priceLowerBound())
                                : cb.and(),
                        Objects.nonNull(request.priceUpperBound()) ?
                                cb.lessThanOrEqualTo(root.get(Product_.PRICE), request.priceUpperBound())
                                : cb.and(),
                        Objects.nonNull(request.currency()) ?
                                cb.equal(root.get(Product_.CURRENCY), request.currency())
                                : cb.and()
                );
    }

    @Override
    public PageRequest buildPage(ProductSearchingRequest request) {
        final List<Sort.Order> fieldOrders = Objects.nonNull(request.sort()) ?
                request.sort().stream()
                        .map(sortOrder -> new Sort.Order(
                                DIRECTION_MAP.get(sortOrder.direction()), FIELD_STRING_MAP.get(sortOrder.field())
                        ))
                        .toList()
                : Collections.emptyList();
        final var orders = new ArrayList<>(fieldOrders);
        orders.add(DEFAULT_ORDER);
        return PageRequest.of(PAGE_SIZE, request.page(), Sort.by(orders));
    }

    private String containsPattern(String entry) {
        return "%" + entry + "%s";
    }
}

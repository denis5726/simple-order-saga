package com.example.simplesaga.order.service.impl;

import com.example.simplesaga.common.exception.ObjectNotFoundApiException;
import com.example.simplesaga.order.dto.ProductCreatingRequest;
import com.example.simplesaga.order.dto.ProductDto;
import com.example.simplesaga.order.dto.ProductPageDto;
import com.example.simplesaga.order.dto.ProductSearchingRequest;
import com.example.simplesaga.order.entity.Category;
import com.example.simplesaga.order.entity.Product;
import com.example.simplesaga.order.exception.InvalidProductCurrencyApiException;
import com.example.simplesaga.order.mapper.ProductMapper;
import com.example.simplesaga.order.repository.CategoryRepository;
import com.example.simplesaga.order.repository.ProductRepository;
import com.example.simplesaga.order.service.ProductFilterBuilder;
import com.example.simplesaga.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Set<Currency> VALID_CURRENCIES = Set.of(
            Currency.getInstance("RUB"),
            Currency.getInstance("USD")
    );
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductFilterBuilder filterBuilder;

    @Override
    public ProductPageDto findByFilters(ProductSearchingRequest request) {
        log.info("Getting products by filters: {}", request);
        return productMapper.toPageDto(
                productRepository.findAll(filterBuilder.buildFilters(request), filterBuilder.buildPage(request))
        );
    }

    @Override
    @Transactional
    public ProductDto create(ProductCreatingRequest request) {
        log.info("Creating product: {}", request);
        validateRequestOrThrowException(request);
        final var product = productMapper.fromDto(request);
        product.setCategories(findCategoriesAndCheckThatAllExists(request.categoryIds()));
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(UUID id, ProductCreatingRequest request) {
        log.info("Updating product with id: {} to: {}", id, request);
        validateRequestOrThrowException(request);
        final var product = productRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundApiException(String.format("Product with id=%s was not found", id))
        );
        product.setCategories(findCategoriesAndCheckThatAllExists(request.categoryIds()));
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting product by id={}", id);
        productRepository.delete(retrieveProductByIdOrThrowException(id));
    }

    private void validateRequestOrThrowException(ProductCreatingRequest request) {
        final var currency = request.currency();
        if (!VALID_CURRENCIES.contains(currency)) {
            log.error("Provided invalid currency for product: {}", currency);
            throw new InvalidProductCurrencyApiException();
        }
    }

    private Set<Category> findCategoriesAndCheckThatAllExists(List<UUID> ids) {
        final var foundCategories = new HashSet<>(categoryRepository.findAllById(ids));
        final var existentCategoryIds = foundCategories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        ids.forEach(id -> {
            if (!existentCategoryIds.contains(id)) {
                log.error("Provided non existent category id={} for product", id);
                throw new ObjectNotFoundApiException(String.format("Category with id=%s was not found", id));
            }
        });
        return foundCategories;
    }

    private Product retrieveProductByIdOrThrowException(UUID id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundApiException(String.format("Product with id=%s was not found", id))
        );
    }
}

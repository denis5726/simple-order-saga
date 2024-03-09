package com.example.simplesaga.order.service.impl;

import com.example.simplesaga.common.exception.ObjectAlreadyExistsApiException;
import com.example.simplesaga.common.exception.ObjectNotFoundApiException;
import com.example.simplesaga.order.dto.CategoryCreatingRequest;
import com.example.simplesaga.order.dto.CategoryDto;
import com.example.simplesaga.order.entity.Category;
import com.example.simplesaga.order.mapper.CategoryMapper;
import com.example.simplesaga.order.repository.CategoryRepository;
import com.example.simplesaga.order.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto findById(UUID id) {
        log.info("Getting category by id={}", id);
        return categoryMapper.toDto(retrieveCategoryByIdOrThrowException(id));
    }

    @Override
    public CategoryDto findByTitle(String title) {
        log.info("Getting category by title={}", title);
        return categoryMapper.toDto(
                categoryRepository.findByTitle(title)
                        .orElseThrow(() -> new ObjectNotFoundApiException(
                                String.format("Category with title=%s was not found", title)
                        ))
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        log.info("Getting all categories");
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryCreatingRequest request) {
        log.info("Creating category by request: {}", request);
        final var title = request.title();
        checkThatTitleIsUniqueOrThrowException(title);
        return categoryMapper.toDto(categoryRepository.save(
                Category.builder()
                        .title(title)
                        .build()
        ));
    }

    @Override
    @Transactional
    public CategoryDto update(UUID id, String title) {
        log.info("Updating category name to {} (id={})", title, id);
        final var category = retrieveCategoryByIdOrThrowException(id);
        checkThatTitleIsUniqueOrThrowException(title);
        category.setTitle(title);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting category with id={}", id);
        final var category = retrieveCategoryByIdOrThrowException(id);
        categoryRepository.delete(category);
    }

    private Category retrieveCategoryByIdOrThrowException(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundApiException(String.format("Category with id=%s was not found", id))
        );
    }

    private void checkThatTitleIsUniqueOrThrowException(String title) {
        if (categoryRepository.existsByTitle(title)) {
            log.error("Attempt to create category with existent title={}", title);
            throw new ObjectAlreadyExistsApiException(String.format("Category with title=%s already exists", title));
        }
    }
}

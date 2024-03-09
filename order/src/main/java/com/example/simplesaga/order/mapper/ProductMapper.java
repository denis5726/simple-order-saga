package com.example.simplesaga.order.mapper;

import com.example.simplesaga.order.dto.ProductCreatingRequest;
import com.example.simplesaga.order.dto.ProductDto;
import com.example.simplesaga.order.dto.ProductPageDto;
import com.example.simplesaga.order.entity.Product;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastEditor", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product fromDto(ProductCreatingRequest request);

    @Mapping(target = "hasNext", ignore = true)
    @Mapping(target = "size", source = "size")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "content", source = "content")
    ProductPageDto toPageDto(Page<Product> products);

    @BeforeMapping
    default void mapHasNext(Page<Product> page, @MappingTarget ProductPageDto dto) {
        dto.setHasNext(page.hasNext());
    }
}

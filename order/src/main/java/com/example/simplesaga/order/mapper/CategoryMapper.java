package com.example.simplesaga.order.mapper;

import com.example.simplesaga.order.dto.CategoryDto;
import com.example.simplesaga.order.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);
}

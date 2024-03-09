package com.example.simplesaga.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductPageDto {
    private List<ProductDto> content;
    private Integer size;
    private Integer number;
    private Boolean hasNext;
}

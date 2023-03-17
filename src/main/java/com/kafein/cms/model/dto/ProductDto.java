package com.kafein.cms.model.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Long stockSize;

    private Long stockDownLimit;

    private Long catalogId;

    private Long warehouseId;
}

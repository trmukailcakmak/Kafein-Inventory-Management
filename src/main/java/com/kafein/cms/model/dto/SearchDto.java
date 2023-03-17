package com.kafein.cms.model.dto;

import lombok.Data;

@Data
public class SearchDto {
    private String name;
    private String area;

    private String city;

    private String district;

    private Long productId;

    private String productName;

    private String catalogName;

    private String tag;
}

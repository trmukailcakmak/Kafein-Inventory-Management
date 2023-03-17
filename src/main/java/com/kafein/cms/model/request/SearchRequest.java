package com.kafein.cms.model.request;

import com.kafein.cms.constant.MessageKey;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchRequest {

    private String name;
    private String area;

    private String city;

    private String district;

    private Long productId;

    private String productName;
    private String catalogName;

    private String tag;

}

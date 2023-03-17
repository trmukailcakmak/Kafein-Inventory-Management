package com.kafein.cms.model.request;

import com.kafein.cms.constant.MessageKey;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    private Long id;


    @NotNull(message = MessageKey.ERR06)
    private String name;

    private String description;

    private Long stockSize;

    private Long stockDownLimit;

    private Long catalogId;

    private Long warehouseId;
}

package com.kafein.cms.model.request;

import com.kafein.cms.constant.MessageKey;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CatalogRequest {

    private Long id;

    @NotNull(message = MessageKey.ERR06)
    private String name;

    private String description;
}

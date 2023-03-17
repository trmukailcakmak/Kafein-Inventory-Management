package com.kafein.cms.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogResponse implements Serializable {
    private Long id;

    private String name;
    private String desc;
}

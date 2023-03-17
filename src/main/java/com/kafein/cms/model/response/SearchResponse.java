package com.kafein.cms.model.response;

import com.kafein.cms.model.dto.CatalogDto;
import com.kafein.cms.model.dto.WarehouseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchResponse implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Long stockSize;

    private Long stockDownLimit;

    private CatalogDto catalogDto;

    private WarehouseDto warehouseDto;
}

package com.kafein.cms.model.response;

import com.kafein.cms.model.dto.CatalogDto;
import com.kafein.cms.model.dto.WarehouseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponse implements Serializable {
    private Long id;

    private String name;

    private String unit;

    private String description;

    private Long stock;

    private Long critical;

    private CatalogDto catalogDto;

    private WarehouseDto warehouseDto;
}

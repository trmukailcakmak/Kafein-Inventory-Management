package com.kafein.cms.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarehouseResponse implements Serializable {
    private Long id;

    private String name;

    private String area;

    private String city;

    private String district;

    private String address;
}

package com.kafein.cms.model.request;

import com.kafein.cms.constant.MessageKey;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WarehouseRequest {

    private Long id;

    @NotNull(message = MessageKey.ERR06)
    private String name;

    @NotNull(message = MessageKey.ERR07)
    private String area;

    @NotNull(message = MessageKey.ERR08)
    private String city;

    @NotNull(message = MessageKey.ERR09)
    private String district;

    @NotNull(message = MessageKey.ERR10)
    private String address;
}

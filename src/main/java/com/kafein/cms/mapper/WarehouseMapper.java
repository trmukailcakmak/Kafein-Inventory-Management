package com.kafein.cms.mapper;

import com.kafein.cms.config.MyMapperConfig;
import com.kafein.cms.model.dto.WarehouseDto;
import com.kafein.cms.model.entity.Warehouse;
import com.kafein.cms.model.request.WarehouseRequest;
import com.kafein.cms.model.response.WarehouseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MyMapperConfig.class)
public abstract class WarehouseMapper {

    public static final WarehouseMapper INSTANCE= Mappers.getMapper(WarehouseMapper.class);

    public abstract  WarehouseDto mapToDtoFromRequest(WarehouseRequest request);
    public abstract Warehouse mapToEntityFromDto(WarehouseDto warehouseDto);
    public abstract WarehouseDto mapToDtoFromEntity(Warehouse entity);
    public abstract List<WarehouseDto> mapToDtoFromEntity(List<Warehouse> entity);

    public abstract WarehouseResponse mapToResponseFromDto(WarehouseDto warehouseDto);
    public abstract List<WarehouseResponse> mapToResponseFromDto(List<WarehouseDto> warehouseDtoList);

}

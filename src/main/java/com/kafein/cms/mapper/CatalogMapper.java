package com.kafein.cms.mapper;

import com.kafein.cms.config.MyMapperConfig;
import com.kafein.cms.model.entity.Catalog;
import com.kafein.cms.model.dto.CatalogDto;
import com.kafein.cms.model.request.CatalogRequest;
import com.kafein.cms.model.response.CatalogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(config = MyMapperConfig.class)
public abstract class CatalogMapper {

    public static final CatalogMapper INSTANCE= Mappers.getMapper(CatalogMapper.class);

    public abstract CatalogDto mapToDtoFromRequest(CatalogRequest catalogRequest);
    public abstract Catalog mapToEntityFromDto(CatalogDto catalogDto);

    public abstract CatalogDto mapToDtoFromEntity(Catalog catalog);

    public abstract List<CatalogDto> mapToDtoFromEntity(List<Catalog> catalogList);

    public abstract CatalogResponse mapToResponseFromDto(CatalogDto catalogDto);

    public abstract List<CatalogResponse> mapToResponseFromDto(List<CatalogDto> catalogDtoList);

}

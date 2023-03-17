package com.kafein.cms.mapper;

import com.kafein.cms.config.MyMapperConfig;
import com.kafein.cms.model.dto.ProductDto;
import com.kafein.cms.model.entity.Product;
import com.kafein.cms.model.request.ProductRequest;
import com.kafein.cms.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MyMapperConfig.class)
public abstract class ProductMapper {

    public static final ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);

    public abstract ProductDto mapToDtoFromRequest(ProductRequest request);
    @Mapping(source = "catalogId",target = "catalog.id")
    @Mapping(source = "warehouseId",target = "warehouse.id")
    public abstract Product mapToEntityFromDto(ProductDto productDto);
    @Mapping(source = "catalog.id",target = "catalogId")
    @Mapping(source = "warehouse.id",target = "warehouseId")
    public abstract ProductDto maptoDtoFromEntity(Product product);

    public abstract List<ProductDto> maptoDtoFromEntity(List<Product> product);

    public abstract ProductResponse maptoResponseFromDto(ProductDto productDto);
    public abstract List<ProductResponse> maptoResponseFromDto(List<ProductDto> productDtoList);

}

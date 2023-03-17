package com.kafein.cms.mapper;

import com.kafein.cms.config.MyMapperConfig;
import com.kafein.cms.model.dto.ProductDto;
import com.kafein.cms.model.dto.SearchDto;
import com.kafein.cms.model.request.SearchRequest;
import com.kafein.cms.model.response.SearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MyMapperConfig.class)
public abstract class SearchMapper {
    public static final SearchMapper INSTANCE= Mappers.getMapper(SearchMapper.class);

    public abstract SearchDto mapToDtoFromRequest(SearchRequest searchRequest);

    public abstract SearchResponse mapToResponseFromDto(ProductDto productDto);
    public abstract List<SearchResponse> mapToResponseFromDto(List<ProductDto> productDto);
}

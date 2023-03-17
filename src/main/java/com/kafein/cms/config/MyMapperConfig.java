package com.kafein.cms.config;

import org.mapstruct.*;

@org.mapstruct.MapperConfig(uses={

},
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
unmappedTargetPolicy = ReportingPolicy.WARN)
public class MyMapperConfig {
}

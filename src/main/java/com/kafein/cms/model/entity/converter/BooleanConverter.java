package com.kafein.cms.model.entity.converter;

import com.kafein.cms.constant.ActionType;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {

        return value.toString();
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return Boolean.valueOf(value);
    }
}

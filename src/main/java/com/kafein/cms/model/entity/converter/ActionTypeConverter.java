package com.kafein.cms.model.entity.converter;

import com.kafein.cms.constant.ActionType;

import javax.persistence.AttributeConverter;

public class ActionTypeConverter implements AttributeConverter<ActionType, String> {
    @Override
    public String convertToDatabaseColumn(ActionType actionType) {
        return actionType.name();
    }

    @Override
    public ActionType convertToEntityAttribute(String name) {
        return ActionType.valueOf(name);
    }
}

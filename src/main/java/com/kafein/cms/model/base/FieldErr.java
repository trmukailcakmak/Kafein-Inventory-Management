package com.kafein.cms.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErr {
    private String field;
    private String message;
}

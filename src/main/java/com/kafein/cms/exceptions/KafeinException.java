package com.kafein.cms.exceptions;

import lombok.Data;

@Data
public class KafeinException extends RuntimeException {
    private String code;
    private Object[] prmList;
    public KafeinException(String code,String ... prmList){
        super();
        this.code = code;
        this.prmList = prmList;
    }
}

package com.kafein.cms.constant;

import lombok.Getter;

@Getter
public enum ActionType {
    INSERT("INSERT"),UPDATE("UPDATE"),DELETE("DELETE"),STOCK_INCREMENT("INCREMENT"),STOCK_DECREMENT("DECREMENT");

    private String code;

    private ActionType(String code){
        this.code = code;
    }
}

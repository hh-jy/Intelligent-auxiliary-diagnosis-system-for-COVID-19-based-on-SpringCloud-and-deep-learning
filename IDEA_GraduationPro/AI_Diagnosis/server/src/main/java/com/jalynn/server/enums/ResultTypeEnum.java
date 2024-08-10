package com.jalynn.server.enums;

import lombok.Getter;

@Getter
public enum ResultTypeEnum {
    POSITIVE(1,"阳性"),
    NEGATIVE(2,"阴性");

    private Integer code;

    private String message;

    ResultTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

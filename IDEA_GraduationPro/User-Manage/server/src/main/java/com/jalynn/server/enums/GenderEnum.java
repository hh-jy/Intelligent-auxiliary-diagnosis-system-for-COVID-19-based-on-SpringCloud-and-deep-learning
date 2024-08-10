package com.jalynn.server.enums;

import lombok.Getter;

/**
 * 用户角色
 */
@Getter
public enum GenderEnum {
    FEMALE(1,"女"),
    MALE(2,"男"),
    ;

    private Integer code;

    private String message;

    GenderEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.jalynn.server.enums;

import lombok.Getter;

/**
 * 用户角色
 */
@Getter
public enum UserRoleEnum {
    DOCTOR(1,"doctor"),
    ADMIN(2,"admin"),
    ;

    private Integer code;

    private String message;

    UserRoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

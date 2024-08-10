package com.jalynn.server.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    LOGIN_ERROR(1,"登陆失败"),
    ROLE_ERROR(2,"角色错误"),
    USER_NULL(3,"不存在该用户"),
    LOGOUT_ERROR(4,"注销失败")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

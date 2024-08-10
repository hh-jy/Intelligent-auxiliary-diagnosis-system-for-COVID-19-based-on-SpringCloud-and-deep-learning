package com.jalynn.server.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    FIND_ERROR(1,"查询失败"),
    UPDATE_ERROR(2,"更新失败"),
    INSERT_ERROR(3,"添加失败")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

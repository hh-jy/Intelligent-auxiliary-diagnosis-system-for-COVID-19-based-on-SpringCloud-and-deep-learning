package com.jalynn.server.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    DIAGNOSIS_NULL_ERROR(1,"诊断失败,诊断图片不能为空！")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

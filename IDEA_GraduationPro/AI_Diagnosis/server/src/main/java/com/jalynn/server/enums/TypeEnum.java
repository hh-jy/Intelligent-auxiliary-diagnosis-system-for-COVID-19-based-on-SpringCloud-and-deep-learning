package com.jalynn.server.enums;

import lombok.Getter;

/**
 * 预测结果的类型
 */
@Getter
public enum TypeEnum {
    COVID("COVID","新型冠状病毒肺炎"),
    Lung_Opacity("Lung_Opacity","肺部浑浊（非COVID肺部感染）"),
    Normal("Normal","正常"),
    Viral_Pneumonia("Viral_Pneumonia","普通病毒性肺炎");

    private String code;

    private String message;

    TypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

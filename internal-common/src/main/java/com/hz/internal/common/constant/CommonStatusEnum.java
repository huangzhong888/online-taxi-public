package com.hz.internal.common.constant;

import lombok.Getter;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 17:12
 * @Description: com.hz.internal.common.constant
 * @version: 1.0
 */
public enum CommonStatusEnum {
    //验证码错误提示
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    //token的提示
    TOKEN_ERROR(1199,"token 错误"),
    SUCCESS(1,"success"),
    FAIL(0,"fail")
    ;

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}

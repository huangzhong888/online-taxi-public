package com.hz.internal.common.constant;

import lombok.Getter;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 17:12
 * @Description: com.hz.internal.common.constant
 * @version: 1.0
 */
public enum CommonStatusEnum {
    //验证码错误提示 1000-1099
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    //token的提示 1100-1199
    TOKEN_ERROR(1199,"token 错误"),

    //用户的提示 1200-1299
    USER_NOT_EXISTS(1200,"用户不存在"),

    //计价规则不存在提示 1300-1399
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

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

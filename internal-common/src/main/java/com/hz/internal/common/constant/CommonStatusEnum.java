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

    //区域地图信息 1400-1499
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),

    //司机和车辆1500-1599
    DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),
    DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆绑定关系已存在，请勿重复绑定"),
    DRIVER_NOT_EXIST(1501,"司机不存在"),
    DRIVER_BIND_EXIST(1503,"司机已经绑定了，请勿重复绑定"),
    CAR_BIND_EXIST(1504,"车辆已经绑定了，请勿重复绑定"),


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

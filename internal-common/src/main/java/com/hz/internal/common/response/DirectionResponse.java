package com.hz.internal.common.response;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 23:06
 * @Description: com.hz.internal.common.response
 * @version: 1.0
 */
@Data
public class DirectionResponse {

    //实体类对象用包装类
    private Integer distance;

    private Integer duration;
}

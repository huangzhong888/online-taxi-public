package com.hz.internal.common.dto;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 11:05
 * @Description: com.hz.internal.common.dto
 * @version: 1.0
 */
@Data
public class DicDistrict {
    //数据库中char长度6
    private String addressCode;

    //varchar
    private String addressName;

    //数据库中char长度6
    private String parentAddressCode;

    //tinyint
    private Integer level;
}

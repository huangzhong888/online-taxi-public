package com.hz.internal.common.dto;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/5 - 11 - 05 - 17:16
 * @Description: com.hz.internal.common.dto
 * @version: 1.0
 */

//计价表实体类
@Data
public class PriceRule {

    //数据库char长度超过1,java中用String
    private String cityCode;

    private String vehicleType;

    private double startFare;

    //数据库int类型
    private int startMile;

    private double unitPricePerMile;

    private double unitPricePerMinute;
}

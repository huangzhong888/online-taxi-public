package com.hz.internal.common.request;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 20:05
 * @Description: com.hz.internal.common.request
 * @version: 1.0
 */
@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;
}

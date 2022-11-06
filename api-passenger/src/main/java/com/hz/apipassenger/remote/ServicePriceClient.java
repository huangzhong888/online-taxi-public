package com.hz.apipassenger.remote;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 8:56
 * @Description: com.hz.apipassenger.remote
 * @version: 1.0
 */

@FeignClient("service-price")
public interface ServicePriceClient {

    @PostMapping("/forecast-price")
    ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}

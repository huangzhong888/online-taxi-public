package com.hz.serviceprice.remote;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/5 - 11 - 05 - 15:57
 * @Description: com.hz.serviceprice.remote
 * @version: 1.0
 */

@FeignClient("service-map")
public interface ServiceMapClient {

    @GetMapping("/direction/driving")
    ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDTO forecastPriceDTO);
}

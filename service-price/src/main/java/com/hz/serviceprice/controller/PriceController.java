package com.hz.serviceprice.controller;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.PriceResponse;
import com.hz.serviceprice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 21:18
 * @Description: com.hz.serviceprice.controller
 * @version: 1.0
 */
@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        priceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(12.22);
        return ResponseResult.success(priceResponse);
    }
}

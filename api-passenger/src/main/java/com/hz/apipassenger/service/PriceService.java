package com.hz.apipassenger.service;

import com.hz.apipassenger.remote.ServicePriceClient;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 20:11
 * @Description: com.hz.apipassenger.service
 * @version: 1.0
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * 根据出发地和目的地的经纬度预估价格
     * @param depLatitude
     * @param depLongitude
     * @param destLatitude
     * @param destLongitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){

        log.info(depLatitude+""+depLongitude+""+destLatitude+""+destLongitude);

        log.info("调用远程计价服务预估价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        return servicePriceClient.forecastPrice(forecastPriceDTO);
    }
}

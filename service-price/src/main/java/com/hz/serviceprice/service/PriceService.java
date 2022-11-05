package com.hz.serviceprice.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.DirectionResponse;
import com.hz.internal.common.response.PriceResponse;
import com.hz.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 22:20
 * @Description: com.hz.serviceprice.service
 * @version: 1.0
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
        log.info(depLatitude+""+depLongitude+""+destLatitude+""+destLongitude);

        log.info("调用地图服务查询距离和时常");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        ResponseResult<DirectionResponse> driving = serviceMapClient.driving(forecastPriceDTO);
        Integer distance = driving.getData().getDistance();
        Integer duration = driving.getData().getDuration();
        log.info("距离"+distance);
        log.info("时常"+duration);

        log.info("读取计价规则");

        log.info("根据距离和时常计算价格");


        return driving;
    }
}

package com.hz.serviceprice.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.PriceResponse;
import lombok.extern.slf4j.Slf4j;
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

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
        log.info(depLatitude+""+depLongitude+""+destLatitude+""+destLongitude);

        log.info("调用地图服务查询距离和时常");

        log.info("读取计价规则");

        log.info("根据距离和时常计算价格");

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(155.45);
        return ResponseResult.success(priceResponse);
    }
}

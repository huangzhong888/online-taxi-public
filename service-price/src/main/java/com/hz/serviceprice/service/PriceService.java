package com.hz.serviceprice.service;

import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.dto.PriceRule;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.DirectionResponse;
import com.hz.internal.common.response.PriceResponse;
import com.hz.serviceprice.mapper.PriceRuleMapper;
import com.hz.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
        log.info(depLatitude+""+depLongitude+""+destLatitude+""+destLongitude);

        log.info("调用地图服务查询距离和时常");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        //远程服务调用
        ResponseResult<DirectionResponse> driving = serviceMapClient.driving(forecastPriceDTO);
        Integer distance = driving.getData().getDistance();
        Integer duration = driving.getData().getDuration();
        log.info("距离"+distance);
        log.info("时常"+duration);

        log.info("读取计价规则");
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");

        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size()==0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离和时常计算价格");
        double price = getPrice(distance, duration, priceRule);

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(price);


        return ResponseResult.success(priceResponse);
    }

    /**
     * 根据距离和时长计算价格
     * @param distance  距离
     * @param duration  时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance,Integer duration,PriceRule priceRule){
        //BigDecimal
        BigDecimal price = new BigDecimal(0);

        //起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        //里程费
        //总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        //总里程 km
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        //起步里程
        BigDecimal startMileDecimal = new BigDecimal(priceRule.getStartMile());

        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();

        //最终收费的里程数
        double mile = distanceSubtract<0?0:distanceSubtract;
        BigDecimal mileDecimal = new BigDecimal(mile);

        //计程价格 元/km
        BigDecimal unitPricePerMile = new BigDecimal(priceRule.getUnitPricePerMile());
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMile).setScale(2, BigDecimal.ROUND_HALF_UP);

        //起步价+里程价
        price = price.add(mileFare);

        //计算时长费
        //秒时长
        BigDecimal time = new BigDecimal(duration);
         //分钟时长
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);

         //获取时长单价 元/分钟
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(priceRule.getUnitPricePerMinute());
        //时长费
        BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);

        //起步价+里程价+时长费
        price = price.add(timeFare).setScale(2,BigDecimal.ROUND_HALF_UP);
        return price.doubleValue();
    }
}

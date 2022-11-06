package com.hz.serviceprice.service;

import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.dto.PriceRule;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.ForecastPriceDTO;
import com.hz.internal.common.response.DirectionResponse;
import com.hz.internal.common.response.PriceResponse;
import com.hz.internal.common.util.BigDecimalUtils;
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

        double price = 0;

        //起步价
        price = BigDecimalUtils.add(price, priceRule.getStartFare());

        //里程费
        //总里程 m转km
        double distanceMile = BigDecimalUtils.divide(distance, 1000);
        //起步里程
        double startMile = priceRule.getStartMile();

        //要计价的里程
        double mileSubtract = BigDecimalUtils.subtract(distanceMile,startMile);

        //最终收费的里程数
        double mile = mileSubtract<0?0:mileSubtract;

        //计程价格 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        double mileFare = BigDecimalUtils.multiply(mile,unitPricePerMile);

        //起步价+里程价
        price = BigDecimalUtils.add(price,mileFare);

        //计算时长费
         //秒转分钟
        double time = BigDecimalUtils.divide(duration, 60);

        //获取时长单价 元/分钟
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //时长费
        double timeFare = BigDecimalUtils.multiply(time,unitPricePerMinute);

        //起步价+里程价+时长费
        price = BigDecimalUtils.add(price,timeFare);

        //设置精度
        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        priceBigDecimal = priceBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();
    }
}

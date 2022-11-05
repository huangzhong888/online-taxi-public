package com.hz.servicemap.remote;

import com.hz.internal.common.constant.AmapConfigConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/5 - 11 - 05 - 9:39
 * @Description: com.hz.servicemap.remote
 * @version: 1.0
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
            //组装请求调用的url
            StringBuilder urlBuild = new StringBuilder();
            urlBuild.append(AmapConfigConstant.DIRECTION_URL);
            urlBuild.append("?");
            urlBuild.append("origin="+depLongitude+","+depLatitude);
            urlBuild.append("&");
            urlBuild.append("destination="+destLongitude+","+destLatitude);
            urlBuild.append("&");
            urlBuild.append("extensions=base&output=json&");
            urlBuild.append("key="+amapKey);
            log.info(urlBuild.toString());
            //调用高德接口(本来想用feign调用，后来发现feign是局域网内的内部调用，要外部调用用RestTemplate)
            ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
            String directionString = directionEntity.getBody();
            //解析接口
             return parseDirectionEntity(directionString);

    }

    /**
     * 解析远程服务返回的json数据
     * @param directionString
     * @return
     */
    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;
        try{
            //最外层
            JSONObject result = JSONObject.fromObject(directionString);
            int status = result.getInt(AmapConfigConstant.STATUS);
            if(status == 1){
                JSONObject route = result.getJSONObject(AmapConfigConstant.ROUTE);
                JSONArray paths = route.getJSONArray(AmapConfigConstant.PATHS);
                JSONObject path0 = paths.getJSONObject(0);
                directionResponse = new DirectionResponse();
                int distance = path0.getInt(AmapConfigConstant.DISTANCE);
                int duration = path0.getInt(AmapConfigConstant.DURATION);
                directionResponse.setDistance(distance);
                directionResponse.setDuration(duration);
            }
        }catch (Exception e){

        }
        return directionResponse;
    }
}

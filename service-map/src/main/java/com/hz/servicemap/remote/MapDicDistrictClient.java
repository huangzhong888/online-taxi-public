package com.hz.servicemap.remote;

import com.hz.internal.common.constant.AmapConfigConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicemap.mapper.DicDistrictMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 16:12
 * @Description: com.hz.servicemap.remote
 * @version: 1.0
 */
@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public String DicDistrict(String keywords){

        //拼接资源请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstant.DISTRICT_URL);
        url.append("?");
        url.append("keywords="+ keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key="+amapKey);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        return forEntity.getBody();
    }

}

package com.hz.servicemap.service;

import com.hz.internal.common.constant.AmapConfigConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 15:41
 * @Description: com.hz.servicemap.service
 * @version: 1.0
 */
@Service
public class DicDistrictService {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords){

        //拼接资源请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstant.DISTRICT_URL);
        url.append("?");
        url.append("keywords="+ keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key="+amapKey);

        //解析结果

        //插入数据库
        return null;
    }
}


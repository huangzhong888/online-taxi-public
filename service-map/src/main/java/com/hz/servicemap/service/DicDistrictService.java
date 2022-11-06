package com.hz.servicemap.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicemap.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 15:41
 * @Description: com.hz.servicemap.service
 * @version: 1.0
 */
@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    public ResponseResult initDicDistrict(String keywords){
        //请求地图
        String dicDistrict = mapDicDistrictClient.DicDistrict(keywords);

        return ResponseResult.success();
    }

}


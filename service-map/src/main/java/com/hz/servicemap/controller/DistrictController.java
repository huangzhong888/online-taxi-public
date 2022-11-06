package com.hz.servicemap.controller;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 15:38
 * @Description: com.hz.servicemap.controller
 * @version: 1.0
 */
@RestController
public class DistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDicDistrict(String keywords){

        return dicDistrictService.initDicDistrict(keywords);

    }
}

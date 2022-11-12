package com.hz.servicedriveruser.controller;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 15:28
 * @Description: com.hz.servicedriveruser.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public ResponseResult test(){
        return driverUserService.testGetDriverUser();
    }
}

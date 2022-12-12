package com.hz.servicedriveruser.controller;

import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 21:05
 * @Description: com.hz.servicedriveruser.controller
 * @version: 1.0
 */
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/users")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }
}

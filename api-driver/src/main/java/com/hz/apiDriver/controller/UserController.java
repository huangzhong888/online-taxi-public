package com.hz.apiDriver.controller;

import com.hz.apiDriver.service.UserService;
import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 10:28
 * @Description: com.hz.apiDriver.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("users")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        return userService.updateUser(driverUser);
    }
}

package com.hz.servicepassengeruser.controller;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import com.hz.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/31 - 10 - 31 - 18:49
 * @Description: com.hz.servicepassengeruser.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //获取手机号参数
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号"+passengerPhone);
        //调用service从数据库查询数据
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone ){

        //调用service层（controller层一般接收参数）
        return  userService.getUserByPhone(passengerPhone);


    }
}

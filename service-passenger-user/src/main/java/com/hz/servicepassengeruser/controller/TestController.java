package com.hz.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/30 - 10 - 30 - 22:18
 * @Description: com.hz.servicepassengeruser.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "黄仲";
    }
}

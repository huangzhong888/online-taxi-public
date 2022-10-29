package com.hz.service.verificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 15:39
 * @Description: com.hz.service.verificationcode.controller
 * @version: 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "黄仲";
    }
}

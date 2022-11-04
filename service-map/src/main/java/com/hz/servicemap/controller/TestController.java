package com.hz.servicemap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 22:50
 * @Description: com.hz.servicemap.controller
 * @version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "你好";
    }
}

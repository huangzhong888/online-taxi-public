package com.hz.apipassenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 19:27
 * @Description: com.hz.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class Hello {
    @GetMapping(value = "/hello")
    public String greet(){
        return "hello,黄仲";
    }
}

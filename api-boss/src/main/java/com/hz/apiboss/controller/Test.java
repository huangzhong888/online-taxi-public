package com.hz.apiboss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/12/15 - 12 - 15 - 18:26
 * @Description: com.hz.apiboss.controller
 * @version: 1.0
 */
@RestController
public class Test {

    @GetMapping("/hello")
    public String test(){
        return "黄仲";
    }
}

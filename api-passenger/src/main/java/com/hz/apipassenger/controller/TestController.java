package com.hz.apipassenger.controller;

import com.hz.internal.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/1 - 11 - 01 - 19:24
 * @Description: com.hz.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    /**
     * 带token访问
     * @return
     */
    @GetMapping("/authtest")
    public ResponseResult auth(){
        return ResponseResult.success("auth test");
    }

    /**
     * 无token访问
     * @return
     */
    @GetMapping("/noauthtest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth test");
    }
}

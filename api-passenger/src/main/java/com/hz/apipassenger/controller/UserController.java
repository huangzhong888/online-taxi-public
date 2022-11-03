package com.hz.apipassenger.controller;

import com.hz.apipassenger.service.UserService;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/3 - 11 - 03 - 20:19
 * @Description: com.hz.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        //从http请求中获取accessToken
        String accessToken = request.getHeader("Authorization");
        //根据accessToken查询
        return userService.getUserByAccessToken(accessToken);


    }
}

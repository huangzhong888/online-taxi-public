package com.hz.apipassenger.controller;

import com.hz.apipassenger.service.TokenService;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/2 - 11 - 02 - 23:29
 * @Description: com.hz.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        //获取refreshToken
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        //调用刷新token的服务重新生成token
        return tokenService.refreshToken(refreshTokenSrc);
    }
}

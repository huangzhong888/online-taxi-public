package com.hz.apipassenger.service;

import com.auth0.jwt.JWT;
import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.constant.TokenConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.dto.TokenResult;
import com.hz.internal.common.response.TokenResponse;
import com.hz.internal.common.util.JwtUtils;
import com.hz.internal.common.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/2 - 11 - 02 - 23:36
 * @Description: com.hz.apipassenger.service
 * @version: 1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc) {
        //解析refreshToken
        TokenResult tokenResult = JwtUtils.parseToken(refreshTokenSrc);

        if(tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        //从redis读取refreshToken
            //先生成key
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.REFRESH_TOKEN);
            //获取值
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        //校验refreshToken
        if((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){

            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token
        String refreshToken = JwtUtils.generatorToken(phone,identity,TokenConstant.REFRESH_TOKEN);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstant.ACCESS_TOKEN);
        //存入redis
            //先生成key
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity,TokenConstant.ACCESS_TOKEN);
            //设置值
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31, TimeUnit.DAYS);

        //封装双token到TokenResponse对象中
        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
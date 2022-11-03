package com.hz.apipassenger.service;

import com.hz.internal.common.dto.PassengerUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.dto.TokenResult;
import com.hz.internal.common.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/3 - 11 - 03 - 20:25
 * @Description: com.hz.apipassenger.service
 * @version: 1.0
 */
@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken"+accessToken);
        //解析token,获取手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号"+phone);
        //根据手机号获取用户信息
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setProfilePhoto("头像");
        passengerUser.setPassengerName("张三");

        return ResponseResult.success(passengerUser);
    }
}

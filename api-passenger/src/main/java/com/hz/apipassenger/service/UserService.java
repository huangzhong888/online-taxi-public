package com.hz.apipassenger.service;

import com.hz.apipassenger.remote.ServicePassengerUserClient;
import com.hz.internal.common.dto.PassengerUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.dto.TokenResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import com.hz.internal.common.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken"+accessToken);
        //解析token,获取手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号"+phone);

        //调用远程服务根据手机号获取用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return  ResponseResult.success(userByPhone.getData());
    }
}

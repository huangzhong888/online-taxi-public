package com.hz.servicepassengeruser.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/31 - 10 - 31 - 19:12
 * @Description: com.hz.servicepassengeruser.service
 * @version: 1.0
 */
@Service
public class UserService {

    public ResponseResult loginOrRegister(String passengerPhone ){
        //进行匹配校验,查询用户

        System.out.println("user service被调用");
        //如果不存在，新增用户

        //存在，则登录
        return ResponseResult.success();
    }
}

package com.hz.apiDriver.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/21 - 02 - 21 - 19:57
 * @Description: com.hz.apiDriver.service
 * @version: 1.0
 */
@Service
public class VerificationCodeService {

    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询service-driver-user服务，查询该手机号的司机是否存在

        //获取验证码

        //调用第三方发送验证码

        //存入redis
        return ResponseResult.success("");
    }
}

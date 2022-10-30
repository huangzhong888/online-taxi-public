package com.hz.apipassenger.service;

import com.hz.apipassenger.remote.ServiceVerificationCodeClient;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 20:06
 * @Description: com.hz.apipassenger.service
 * @version: 1.0
 */
@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    private String verificationCodePrefix = "passenger-verification-Code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generateCode(String passengerPhone){

        //调用验证码服务获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse =serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        //key,value,过期时间
        String key = verificationCodePrefix + passengerPhone;
        // 存入Redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商，验证码发送到手机上，比如阿里短信服务、腾讯短信通、华信、容联

        //返回值
        return ResponseResult.success("");

    }
}

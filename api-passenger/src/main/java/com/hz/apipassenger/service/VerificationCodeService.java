package com.hz.apipassenger.service;

import com.hz.apipassenger.remote.ServiceVerificationCodeClient;
import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.NumberCodeResponse;
import com.hz.internal.common.response.TokenResponse;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 生成验证码
     * @param passengerPhone
     * @return
     */
    public ResponseResult generateCode(String passengerPhone){

        //调用验证码服务获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse =serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        //key,value,过期时间
        String key = generateKeyByPhone(passengerPhone);
        // 存入Redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商，验证码发送到手机上，比如阿里短信服务、腾讯短信通、华信、容联

        //返回值
        return ResponseResult.success("");
    }

    /**
     * 根据手机号生成key
     * @return
     */
    public String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }
    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){
        //根据手机号去redis读取验证码
        System.out.println("根据手机号去redis读取验证码");
        //生成key
        String key = generateKeyByPhone(passengerPhone);
        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value"+codeRedis);

        //对传进来的验证码进行校验
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(),CommonStatusEnum.FAIL.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(),CommonStatusEnum.FAIL.getValue());
        }
        System.out.println("对传进来的验证码进行校验");

        //判断原来是否有用户，有即为登录，否则注册
        System.out.println("判断原来是否有用户，有即为登录，否则注册");
        //颁发令牌
        System.out.println("颁发令牌");
        //响应结果
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}

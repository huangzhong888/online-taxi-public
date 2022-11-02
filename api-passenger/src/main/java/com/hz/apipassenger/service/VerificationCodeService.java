package com.hz.apipassenger.service;

import com.hz.apipassenger.remote.ServicePassengerUserClient;
import com.hz.apipassenger.remote.ServiceVerificationCodeClient;
import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.constant.IdentityConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import com.hz.internal.common.response.NumberCodeResponse;
import com.hz.internal.common.response.TokenResponse;
import com.hz.internal.common.util.JwtUtils;
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

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    //验证码的前缀
    private String verificationCodePrefix = "passenger-verification-Code-";

    //token的前缀
    private String tokenPrefix = "token-";

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
     * 生成token的键
     * @param Phone
     * @param identity
     * @return
     */
    public String generateTokenKey(String Phone,String identity){
        return tokenPrefix+Phone + "-"+identity;
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

        //(进行远程服务调用)判断原来是否有用户，有即为登录，否则注册
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        //颁发令牌
        String token = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        //将token存到redis
        String tokenKey = generateTokenKey(passengerPhone,IdentityConstant.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(tokenKey,token,30,TimeUnit.DAYS);

        //响应结果
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}

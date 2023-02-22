package com.hz.apiDriver.service;

import com.hz.apiDriver.remote.ServiceDriverUserClient;
import com.hz.apiDriver.remote.ServiceVerificationCodeClient;
import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.constant.DriverCarConstants;
import com.hz.internal.common.constant.IdentityConstant;
import com.hz.internal.common.constant.TokenConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import com.hz.internal.common.response.DriverUserExistsResponse;
import com.hz.internal.common.response.NumberCodeResponse;
import com.hz.internal.common.response.TokenResponse;
import com.hz.internal.common.util.JwtUtils;
import com.hz.internal.common.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/21 - 02 - 21 - 19:57
 * @Description: com.hz.apiDriver.service
 * @version: 1.0
 */
@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询service-driver-user服务，查询该手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        int ifExists = data.getIfExists();
        if(ifExists == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_NOT_EXIST.getValue());
        }
        log.info(driverPhone+" 的司机存在");
        //获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResult = serviceVerificationCodeClient.getNumberCode(6);
        NumberCodeResponse numberCodeResponse = numberCodeResult.getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("验证码"+ numberCode);
        //调用第三方发送验证码,例如阿里短信服务、腾讯等

        //存入redis 1.生成key 2.存入value
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        return ResponseResult.success("");
    }

    /**
     * 校验验证码
     * @param driverPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String driverPhone,String verificationCode){
        //根据手机号去redis读取验证码
        System.out.println("根据手机号去redis读取验证码");
        //生成key
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone,IdentityConstant.DRIVER_IDENTITY);
        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value"+codeRedis);

        //对传进来的验证码进行校验
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(),CommonStatusEnum.FAIL.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }


        //颁发令牌
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.ACCESS_TOKEN);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.REFRESH_TOKEN);


        //将accessToken存到redis
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenConstant.ACCESS_TOKEN);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);

        //将refreshToken存到redis
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenConstant.REFRESH_TOKEN);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);

        //响应结果
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}

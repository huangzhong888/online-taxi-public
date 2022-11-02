package com.hz.internal.common.util;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/2 - 11 - 02 - 10:39
 * @Description: com.hz.internal.common.util
 * @version: 1.0
 */
public class RedisPrefixUtils {

    //验证码的前缀
    private static String verificationCodePrefix = "passenger-verification-Code-";

    //token的前缀
    private static String tokenPrefix = "token-";
    /**
     * 根据手机号生成key
     * @return
     */
    public static String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 生成token的键
     * @param Phone
     * @param identity
     * @return
     */
    public static String generateTokenKey(String Phone,String identity){
        return tokenPrefix+Phone + "-"+identity;
    }
}

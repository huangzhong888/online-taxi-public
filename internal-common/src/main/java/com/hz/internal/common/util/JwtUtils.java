package com.hz.internal.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hz.internal.common.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/1 - 11 - 01 - 17:30
 * @Description: com.hz.internal.common.util
 * @version: 1.0
 */
public class JwtUtils {
    public static final String JWT_KEY_PHONE = "passengerPhone";

    //乘客是1 司机是2
    public static final String JWT_KEY_IDENTITY = "identity";

    //盐
    public static final String SIGN = "hz!@#$";

    //token的类型
    public static final String JWT_TOKEN_TYPE = "tokenType";

    //token时间
    public static final String JWT_TOKEN_TIME =  "token time";

    /**
     * 生成toke
     *
     * @param passengerPhone
     * @return
     */
    public static String generatorToken(String passengerPhone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);

        //token生成时间
        Calendar calendar = Calendar.getInstance();
        map.put(JWT_TOKEN_TIME, calendar.getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        //整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );
       /* //整合过期时间
        builder.withExpiresAt(date);*/

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    /**
     * 校验token,主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;

        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {

        }
        return tokenResult;
    }
}
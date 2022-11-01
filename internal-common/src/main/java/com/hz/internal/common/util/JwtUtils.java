package com.hz.internal.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    public static final String JWT_KEY = "passengerPhone";

    //盐
    public static final String SIGN = "hz!@#$";

    /**
     * 生成toke
     * @param passengerPhone
     * @return
     */
    private static String generatorToken(String passengerPhone){
        Map<String,String> map = new HashMap<>();
        map.put("JWT_KEY",passengerPhone);
        //token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();

        //整合map
        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );
        //整合过期时间
        builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }
}

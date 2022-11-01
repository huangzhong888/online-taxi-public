package com.hz.internal.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/1 - 11 - 01 - 17:30
 * @Description: com.hz.internal.common.util
 * @version: 1.0
 */
public class JwtUtils {

    //盐
    public static final String SIGN = "hz!@#$";

    //生成token
    private static String generatorToken(Map<String,String> map){
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
}

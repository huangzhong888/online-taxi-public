package com.hz.apiDriver.interceptor;

import com.hz.internal.common.constant.TokenConstant;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.dto.TokenResult;
import com.hz.internal.common.util.JwtUtils;
import com.hz.internal.common.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/1 - 11 - 01 - 20:43
 * @Description: com.hz.apipassenger.interceptor
 * @version: 1.0
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("authorization");

        //解析token
        TokenResult tokenResult = JwtUtils.checkToken(token);

        if(tokenResult == null){
            resultString = "access token invalid";
            result = false;
        }else {
            //拼接key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN);
            //从redis中取出token判断
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if((StringUtils.isBlank(tokenRedis)) || (!token.trim().equals(tokenRedis.trim()))){
                resultString = "access token invalid";
                result = false;
            }
        }

        if(!result){
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(ResponseResult.fail(resultString).toString()));
        }
        return result;
    }
}

package com.hz.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 20:06
 * @Description: com.hz.apipassenger.service
 * @version: 1.0
 */
@Service
public class VerificationCodeService {

    public String generateCode(String passengerPhone){
        //调用验证码服务获取验证码
        System.out.println("调用验证码服务获取验证码");
        String code = "456626";

        // 存入Redis
        System.out.println("存入Redis");

        //返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");

        //json类型的数据转Java的String对象返回

        return result.toString() ;
    }
}

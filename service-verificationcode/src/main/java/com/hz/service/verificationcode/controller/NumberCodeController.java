package com.hz.service.verificationcode.controller;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.NonWritableChannelException;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 16:19
 * @Description: com.hz.service.verificationcode.controller
 * @version: 1.0
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult number(@PathVariable("size") int size){
        //生成验证码
        int numberCode = (int)((Math.random() * 9 + 1) * Math.pow(10, size-1));

        //定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(numberCode);


        return ResponseResult.success(response);
    }
}

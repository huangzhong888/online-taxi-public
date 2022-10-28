package com.hz.apipassenger.controller;

import com.hz.apipassenger.request.VerificationCodeDTO;
import com.hz.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 19:48
 * @Description: com.hz.apipassenger.controller
 * @version: 1.0
 */
@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的参数是"+passengerPhone);
        String s = verificationCodeService.generateCode(passengerPhone);
        return s;
    }
}

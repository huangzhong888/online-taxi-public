package com.hz.apipassenger.controller;

import com.hz.apipassenger.service.VerificationCodeService;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return verificationCodeService.generateCode(passengerPhone);
    }


    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("手机号"+passengerPhone+"验证码"+verificationCode);
        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }
}

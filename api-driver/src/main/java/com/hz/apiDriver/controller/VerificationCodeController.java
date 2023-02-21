package com.hz.apiDriver.controller;

import com.hz.apiDriver.service.VerificationCodeService;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/21 - 02 - 21 - 19:52
 * @Description: com.hz.apiDriver.controller
 * @version: 1.0
 */
@RestController
@Slf4j
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码"+driverPhone);
       return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }

}

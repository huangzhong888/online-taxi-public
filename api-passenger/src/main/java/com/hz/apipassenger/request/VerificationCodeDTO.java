package com.hz.apipassenger.request;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 19:55
 * @Description: com.hz.apipassenger.request
 * @version: 1.0
 */
@Data
public class VerificationCodeDTO {
       private String passengerPhone;
       private String verificationCode;
}

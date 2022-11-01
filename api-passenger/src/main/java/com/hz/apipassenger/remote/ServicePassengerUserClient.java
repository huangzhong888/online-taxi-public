package com.hz.apipassenger.remote;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/1 - 11 - 01 - 10:42
 * @Description: com.hz.apipassenger.remote
 * @version: 1.0
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("/user")
     ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}

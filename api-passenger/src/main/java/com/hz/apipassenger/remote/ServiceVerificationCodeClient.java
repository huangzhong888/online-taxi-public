package com.hz.apipassenger.remote;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/30 - 10 - 30 - 10:31
 * @Description: com.hz.apipassenger.remote
 * @version: 1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    @GetMapping("/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size")int size);

}

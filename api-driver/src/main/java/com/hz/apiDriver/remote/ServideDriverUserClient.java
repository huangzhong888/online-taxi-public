package com.hz.apiDriver.remote;

import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 10:32
 * @Description: com.hz.apiDriver.remote
 * @version: 1.0
 */
@FeignClient("service-driver-user")
public interface ServideDriverUserClient {

    @PutMapping("/users")
    ResponseResult updateUser(@RequestBody DriverUser driverUser);
}

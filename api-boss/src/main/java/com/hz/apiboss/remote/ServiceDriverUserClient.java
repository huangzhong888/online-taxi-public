package com.hz.apiboss.remote;

import com.hz.internal.common.dto.Car;
import com.hz.internal.common.dto.DriverCarBindingRelationship;
import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: huangzhong
 * @Date: 2022/12/15 - 12 - 15 - 19:10
 * @Description: com.hz.apiboss.remote
 * @version: 1.0
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @PostMapping("/users")
     ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @PutMapping("/users")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @PostMapping("/car")
    ResponseResult addCar(@RequestBody Car car);

    @PostMapping("/driver-car-binding-relationship/bind")
    ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @PostMapping("/driver-car-binding-relationship/unbind")
    ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}

package com.hz.apiboss.controller;

import com.hz.apiboss.service.CarService;
import com.hz.apiboss.service.DriverUserService;
import com.hz.internal.common.dto.Car;
import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2022/12/15 - 12 - 15 - 18:56
 * @Description: com.hz.apiboss.controller
 * @version: 1.0
 */
@RestController
public class DriverUserController {
    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private CarService carService;

    /**
     * 添加司机
     * @param driverUser
     * @return
     */
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);
    }


    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return  driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 添加车辆信息
     */
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }
}

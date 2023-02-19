package com.hz.servicedriveruser.controller;


import com.hz.internal.common.dto.Car;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄仲
 * @since 2023-02-12
 */
@RestController

public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
}

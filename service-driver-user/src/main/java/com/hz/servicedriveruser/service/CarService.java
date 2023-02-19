package com.hz.servicedriveruser.service;

import com.hz.internal.common.dto.Car;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 16:14
 * @Description: com.hz.servicedriveruser.service
 * @version: 1.0
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car){
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());
         carMapper.insert(car);
         return ResponseResult.success("");
    }
}

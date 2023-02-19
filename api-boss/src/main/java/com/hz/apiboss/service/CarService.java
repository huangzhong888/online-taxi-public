package com.hz.apiboss.service;

import com.hz.apiboss.remote.ServiceDriverUserClient;
import com.hz.internal.common.dto.Car;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/19 - 02 - 19 - 20:22
 * @Description: com.hz.apiboss.service
 * @version: 1.0
 */
@Service
public class CarService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }
}

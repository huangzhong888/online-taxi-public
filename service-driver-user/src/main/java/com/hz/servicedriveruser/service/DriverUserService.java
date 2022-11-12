package com.hz.servicedriveruser.service;

import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 15:52
 * @Description: com.hz.servicedriveruser.service
 * @version: 1.0
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult testGetDriverUser(){
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }
}

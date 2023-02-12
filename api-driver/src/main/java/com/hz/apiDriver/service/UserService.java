package com.hz.apiDriver.service;

import com.hz.apiDriver.remote.ServideDriverUserClient;
import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 10:30
 * @Description: com.hz.apiDriver.service
 * @version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private ServideDriverUserClient servideDriverUserClient;

    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        return servideDriverUserClient.updateUser(driverUser);
    }
}

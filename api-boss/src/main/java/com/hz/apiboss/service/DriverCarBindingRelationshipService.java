package com.hz.apiboss.service;

import com.hz.apiboss.remote.ServiceDriverUserClient;
import com.hz.internal.common.dto.DriverCarBindingRelationship;
import com.hz.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/19 - 02 - 19 - 21:20
 * @Description: com.hz.apiboss.service
 * @version: 1.0
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}

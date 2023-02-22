package com.hz.servicedriveruser.controller;

import com.hz.internal.common.dto.DriverUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.response.DriverUserExistsResponse;
import com.hz.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 21:05
 * @Description: com.hz.servicedriveruser.controller
 * @version: 1.0
 */
@RestController
@Slf4j
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 新增司机用户信息
     * @param driverUser
     * @return
     */
    @PostMapping("/users")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/users")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 查询司机信息
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult getUser(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDB = driverUserByPhone.getData();
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        int ifExists= 1;
        if(driverUserDB == null){
            ifExists = 0;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverUserDB.getDriverPhone());
            response.setIfExists(ifExists);
        }
         return ResponseResult.success(response);
    }
}

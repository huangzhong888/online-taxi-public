package com.hz.servicedriveruser.controller;


import com.hz.internal.common.dto.DriverUserWorkStatus;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicedriveruser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄仲
 * @since 2023-02-22
 */
@RestController
public class DriverUserWorkStatusController {

    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){

        return driverUserWorkStatusService.changeStatus(driverUserWorkStatus.getDriverId(),driverUserWorkStatus.getWorkStatus());
    }
}

package com.hz.servicepassengeruser.service;

import com.hz.internal.common.dto.ResponseResult;
import com.hz.internal.common.request.VerificationCodeDTO;
import com.hz.servicepassengeruser.dto.PassengerUser;
import com.hz.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/31 - 10 - 31 - 19:12
 * @Description: com.hz.servicepassengeruser.service
 * @version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone ){
        //进行匹配校验,查询用户
        Map<String , Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size()==0?"无记录":passengerUsers.get(0).getPassengerPhone());
        System.out.println("user service被调用");
        //如果不存在，新增用户

        //存在，则登录
        return ResponseResult.success();
    }
}

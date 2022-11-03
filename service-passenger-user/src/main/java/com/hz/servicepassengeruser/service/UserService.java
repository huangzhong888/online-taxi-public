package com.hz.servicepassengeruser.service;

import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.dto.PassengerUser;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        //进行匹配校验,查询用户信息
        Map<String , Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        //判断用户是否存在
        if(passengerUsers.size()==0){
            //用户不存在，新增用户
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte)0);

            passengerUserMapper.insert(passengerUser);
        }


        //存在，则登录
        return ResponseResult.success();
    }

    /**
     * 根据手机号查询用户
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone){
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passengerPhone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        if(passengerUsers.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getValue());
        }else {
           //从返回的map集合中拿到用户(其实只有一个用户放在map集合中)
            PassengerUser passengerUser = passengerUsers.get(0);
            //返回
            return ResponseResult.success(passengerUser);
        }
    }

}

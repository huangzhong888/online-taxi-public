package com.hz.internal.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/31 - 10 - 31 - 20:25
 * @Description: com.hz.servicepassengeruser.dto
 * @version: 1.0
 */

@Data
public class PassengerUser {
    //bigint类型对应Java中Long类型
    private Long id;

    //datetime类型对应Java中LocalDateTime类型 ,数据库表中是下划线区分单词，Java中尽量用驼峰写一起
    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;
    //tinyint类型对应java中的byte类型
    private byte passengerGender;

    private byte state;

    private String profilePhoto;
}

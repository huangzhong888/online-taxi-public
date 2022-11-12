package com.hz.servicedriveruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 15:04
 * @Description: com.hz.servicedriveruser
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.hz.servicedriveruser.mapper")
public class ServiceDriverUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class);
    }
}

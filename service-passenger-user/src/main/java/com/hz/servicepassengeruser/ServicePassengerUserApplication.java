package com.hz.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/30 - 10 - 30 - 22:22
 * @Description: com.hz.servicepassengeruser
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.hz.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}

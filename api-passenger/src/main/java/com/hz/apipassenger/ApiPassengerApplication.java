package com.hz.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/28 - 10 - 28 - 19:24
 * @Description: com.hz.apipassenger
 * @version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ApiPassengerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class);
    }
}

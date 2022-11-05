package com.hz.serviceprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 20:55
 * @Description: com.hz.service.price
 * @version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServicePriceApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServicePriceApplication.class);
    }
}

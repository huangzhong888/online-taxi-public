package com.hz.serviceprice;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.hz.serviceprice.mapper")
public class ServicePriceApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServicePriceApplication.class);
    }
}

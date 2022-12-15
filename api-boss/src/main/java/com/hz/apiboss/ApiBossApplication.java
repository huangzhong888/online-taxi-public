package com.hz.apiboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: huangzhong
 * @Date: 2022/12/15 - 12 - 15 - 18:22
 * @Description: com.hz.apiboss
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiBossApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBossApplication.class);
    }
}

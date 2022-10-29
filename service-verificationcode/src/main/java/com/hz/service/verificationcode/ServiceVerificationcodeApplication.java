package com.hz.service.verificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 15:36
 * @Description: com.hz.service.verificationcode
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerificationcodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationcodeApplication.class);
    }
}

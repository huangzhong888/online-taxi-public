package com.hz.apiDriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 10:14
 * @Description: com.hz.apiDriver
 * @version: 1.0
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class ApiDriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class);
    }
    @GetMapping("test")
    public String test(){
        return "test";
    }
}

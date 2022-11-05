package com.hz.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/4 - 11 - 04 - 22:47
 * @Description: com.hz.servicemap
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class);
    }

    //注入RestTemplate对象，用这个对象进行外部的远程调用
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

package com.hz.apipassenger.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/2 - 11 - 02 - 9:35
 * @Description: com.hz.apipassenger.interceptor
 * @version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())

        //拦截的路径
        .addPathPatterns("/**")
        //不拦截的路径
        .excludePathPatterns("/noauthtest");
    }
}

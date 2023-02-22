package com.hz.servicedriveruser.generator;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 15:53
 * @Description: com.hz.servicedriveruser.generator
 * @version: 1.0
 */

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 */
public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8","root","root")
                .globalConfig(builder -> {
                    builder.author("黄仲").fileOverride().outputDir("C:\\Users\\ASUS\\Desktop\\飞滴出行网约车\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.hz.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "C:\\Users\\ASUS\\Desktop\\飞滴出行网约车\\service-driver-user\\src\\main\\java\\com\\hz\\servicedriveruser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_user_work_status");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

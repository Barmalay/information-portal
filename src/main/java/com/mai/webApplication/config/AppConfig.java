package com.mai.webApplication.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mai.webApplication.config")
@EnableFeignClients(
        basePackages = "com.mai.webApplication.proxy")
public class AppConfig {
}

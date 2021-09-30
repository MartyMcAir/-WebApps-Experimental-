package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {
    @Bean("myBeanA")
    public String myBeanA() {
        return "My Bean A";
    }
}
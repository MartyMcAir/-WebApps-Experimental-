package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRunner {
    //public class AppRunner implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }

}
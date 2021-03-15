package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/myContextPath");
        SpringApplication.run(AppRunner.class, args);
    }
}
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/myContextPath");
        SpringApplication.run(AppRunner.class, args);
    }

    // https://reflectoring.io/spring-boot-execute-on-startup/
    @PostConstruct
    private void init() {
    }
}
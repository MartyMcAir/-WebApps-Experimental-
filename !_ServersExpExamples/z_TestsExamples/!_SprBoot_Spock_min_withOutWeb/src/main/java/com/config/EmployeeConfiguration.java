package com.config;

import com.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com"})
public class EmployeeConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

}
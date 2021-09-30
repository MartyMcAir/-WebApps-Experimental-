package com.config;

import com.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CustomerConfiguration {

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

}

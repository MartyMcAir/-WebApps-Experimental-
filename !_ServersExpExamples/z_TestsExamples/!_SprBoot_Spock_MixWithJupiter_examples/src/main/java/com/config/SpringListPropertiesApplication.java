package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "lists.properties")
public class SpringListPropertiesApplication {

}

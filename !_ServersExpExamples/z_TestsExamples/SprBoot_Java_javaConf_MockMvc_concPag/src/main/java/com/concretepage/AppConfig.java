package com.concretepage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.concretepage")
@EnableWebMvc
public class AppConfig {
	@Bean
	public MyService myService() {
		return new MyService();
	}
}

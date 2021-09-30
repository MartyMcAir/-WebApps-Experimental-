package com;

import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

// https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5#create-a-java-rest-api-with-spring-boot-for-your-junit-5-testing
@SpringBootApplication(scanBasePackages = {"com"})
public class SpringBootRestApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApp.class, args);
    }

    @Autowired
    UserService userService;

    /**
     * Creates a few sample users.
     */
    @PostConstruct
    public void init() {
//        IntStream.range(0, 8).forEach(index -> {
//            userService.register(new Username("user" + index), Password.raw("foobar"));
//        });
    }

    /**
     * A Spring Security {@link PasswordEncoder} to encrypt passwords for newly created users, used in
     * {@link UserService}.
     *
     * @return
     */
    public @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
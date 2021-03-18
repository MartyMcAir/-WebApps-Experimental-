package com.javatpoint;

import com.javatpoint.model.Student;
import com.javatpoint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootH2DatabaseExampleApplication {
    @Autowired
    StudentService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2DatabaseExampleApplication.class, args);
    }

    // https://reflectoring.io/spring-boot-execute-on-startup/
    @PostConstruct
    private void init() {
        for (int i = 0; i < 15; i++)
            service.saveOrUpdate(new Student(i, "student # is: " + i, i, "mail@ff" + i + ".com"));
    }
}

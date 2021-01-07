package com.model;

import com.model.valueObject.Documents;
import com.validators.annotations.Health;
import com.validators.annotations.HealthConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    @Size(min = 2, max = 30, message = "required min 2 max 30")
    private String name;

    @Size(min = 1, max = 9)
    private String pass;

    //    @PersonAgeConstraint // nested exception is javax.validation.ValidationException
    //    @Min(18)
    //    @Max(100)
    @NotNull
    @Digits(integer = 3, fraction = 0, message = "Не более 3-х знаков")
    private Integer age;

    //    @Phone // work _)
    //    @ContactNumberConstraint // work _)
    @Pattern(regexp = "[0-9]+") // work _)
    @Size(min = 8, max = 14)
    private String phone;

    // https://www.journaldev.com/2668/spring-validation-example-mvc-validator
    @Past
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    // https://habr.com/ru/post/424819/   // dnt understand
    @HealthConstraint(groups = Health.class)
    private Documents healthDocuments;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Documents getHealthDocuments() {
        return healthDocuments;
    }

    public void setHealthDocuments(Documents healthDocuments) {
        this.healthDocuments = healthDocuments;
    }

    public Date getBirthday() {
        return birthday;
    }

    //    public void setBirthday(Date birthday) {
    public void setBirthday(String birthday) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            this.birthday = simpleDateFormat.parse(birthday);
        } catch (ParseException ignore) {
        }
    }
}
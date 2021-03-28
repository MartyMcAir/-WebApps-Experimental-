package com.boots.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// https://www.baeldung.com/properties-with-spring - property place holder
// DON'T WORK
@Configuration
@PropertySource("classpath:myProp.properties")
public class PropertyPlaceHolder {

    @Value("${folder.is}")
    private static String str1;

    public static void main(String[] args) {
        System.out.println("---------------------------------------------" + str1);
    }
}
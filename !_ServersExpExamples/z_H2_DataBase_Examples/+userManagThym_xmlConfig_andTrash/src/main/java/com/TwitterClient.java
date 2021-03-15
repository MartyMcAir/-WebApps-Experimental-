package com;

import com.model.EmployeeEntity;
import com.utils.EmployeeUtils;
import com.validator.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// from C:\z_dev\a_Tmp2\rest-client\src\main\java\com\habuma\twitter\client\TwitterClient.java
public class TwitterClient {
    public static void main(String[] args) {
//        twiiterClientOrigMethodWithErr();
    }

    private static void twiiterClientOrigMethodWithErr() {
        ApplicationContext context = new ClassPathXmlApplicationContext("twitter-client.xml");

        RestTemplate rest = context.getBean(RestTemplate.class);

        // org.springframework.web.client.HttpClientErrorException$Forbidden: 403 Forbidden
        String result = rest.getForObject("http://api.twitter.com/users/show/springinaction.json", String.class);
        System.out.println(result);
    }
}
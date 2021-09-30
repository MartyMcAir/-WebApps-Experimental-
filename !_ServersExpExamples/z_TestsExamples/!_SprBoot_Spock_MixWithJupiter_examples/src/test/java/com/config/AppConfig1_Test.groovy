package com.config

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig1.class)
class AppConfig1_Test extends Specification {
    @Autowired
    private ApplicationContext context;

    public void testMethod1() {
        expect:
        true == context.containsBean("myBeanA")
    }
}
package com.config;

import com.config.AppConfig1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextHierarchy({
        @ContextConfiguration(classes = AppConfig1.class)
})
public class AppConfig1Test {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testMethod1() {
        printAllBeans();
        assertEquals(true, context.containsBean("myBeanA"));
    }

    void printAllBeans() {
        ApplicationContext ctx = context;
        while (ctx != null) {
            System.out.println("---------");
            List.of(ctx.getBeanNamesForType(String.class))
                    .forEach(s -> System.out.println(s));
            ctx = ctx.getParent();
        }
    }
}
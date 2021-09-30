package com.concretepage;

import com.concretepage.config.AppConfig;
import com.concretepage.service.MyService1;
import com.concretepage.service.MyService2;
import com.concretepage.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class MyAppTest1_SpyBean {
    @SpyBean
    private MyService1 myService1;

    @SpyBean
    private MyService2 myService2;

    @Autowired
    private UserService userService;

    @Test
    public void testApp1() {
        Mockito.when(myService1.getMessage()).thenReturn("Welcome");
        String msg = myService1.getMessage();
        assertEquals("Welcome", msg);
    }

    @Test
    public void testApp2() {
        Mockito.when(myService2.getCount()).thenReturn(100);
        int count = userService.getUserCount();
        assertEquals(100, count);
    }
}
package com.concretepage;

import com.concretepage.config.AppConfig;
import com.concretepage.service.MyService1;
import com.concretepage.service.MyService2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.mock.mockito.SpyBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@SpyBeans({
  @SpyBean(MyService1.class),
  @SpyBean(MyService2.class)
})
public class MyAppTest2_SpyBean {
    @Autowired
    private MyService1 myService1;
    
    @Autowired  
    private MyService2 myService2;    
    
    @Test
    public void testApp1() {
        Mockito.when(myService1.getMessage()).thenReturn("Welcome");
        String msg = myService1.getMessage();
        assertEquals("Welcome", msg);
    }	 
    
    @Test
    public void testApp2() {
        Mockito.when(myService2.getCount()).thenReturn(100);
        int count = myService2.getCount();
        assertEquals(100, count);
    }    
}
package com.concretepage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.concretepage.config.AppConfig;
import com.concretepage.service.Animal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class MyAppTest4 {
    @MockBean
    @Qualifier("deer")
    private Animal animal1;

    @MockBean
    @Qualifier("fox")
    private Animal animal2;

    @Test
    public void testApp1() {
        Mockito.when(animal1.getName()).thenReturn("xxx");
        assertEquals("xxx", animal1.getName());
    }

    @Test
    public void testApp2() {
        Mockito.when(animal2.getName()).thenReturn("yyy");
        assertEquals("yyy", animal2.getName());
    }
}
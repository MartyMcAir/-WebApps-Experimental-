package com.concretepage;

import com.concretepage.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class MockitoSpyTest {
    @SpyBean
    private Person person;

    @Test
    public void test1() {
        String name = person.getName();
        assertEquals("Shiva", name);
    }

    @Test
    public void test2() {
        Mockito.when(person.getAge()).thenReturn(20);
        int age = person.getAge();
        assertEquals(20, age);
    }
}
package com.validators;

import com.model.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.DataBinder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
//@SpringBootTest
// https://habr.com/ru/post/424819/
public class ValidationSpringTest {
    // указываем файл сообщений
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    static {
        messageSource.setBasename("message");
    }

    @Autowired
    private PersonValidator personValidator;

    @Test
    public void testValidators() {
        final Employee person = new Employee("Иван Петров", -4500);

        final DataBinder dataBinder = new DataBinder(person);
        dataBinder.addValidators(personValidator);
        dataBinder.validate();

        assertTrue(dataBinder.getBindingResult().hasErrors());

        if (dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> System.out.println(messageSource
                            .getMessage(e, Locale.getDefault())));
        }
    }
}

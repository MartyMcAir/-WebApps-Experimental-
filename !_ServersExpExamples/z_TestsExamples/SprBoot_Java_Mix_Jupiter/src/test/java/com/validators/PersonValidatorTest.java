package com.validators;

import com.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.DataBinder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
// https://habr.com/ru/post/424819/
class PersonValidatorTest {
    // указываем файл сообщений
//    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//    static {
//        messageSource.setBasename("message");
//    }

    //    @Autowired
    private final PersonValidator personValidator = new PersonValidator();

    @Test
    void validate() {
        final Person person = new Person("Иван Петров", -4500);
        final DataBinder dataBinder = new DataBinder(person);
        dataBinder.addValidators(personValidator);
        dataBinder.validate();

        assertTrue(dataBinder.getBindingResult().hasErrors());

        // No message found under code 'value.negative' for locale 'ru_RU'
//        if (dataBinder.getBindingResult().hasErrors()) {
//            dataBinder.getBindingResult().getAllErrors().stream().
//                    forEach(e -> System.out.println(messageSource
//                            .getMessage(e, Locale.getDefault())));
//        }
    }
}
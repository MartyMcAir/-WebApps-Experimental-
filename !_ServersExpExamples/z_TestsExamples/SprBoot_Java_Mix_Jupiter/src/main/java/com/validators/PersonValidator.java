package com.validators;

import com.model.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Person p = (Person) obj;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "value.negative");
        }
    }
}
package com.validators;

import com.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Employee p = (Employee) obj;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "value.negative");
        }
    }
}

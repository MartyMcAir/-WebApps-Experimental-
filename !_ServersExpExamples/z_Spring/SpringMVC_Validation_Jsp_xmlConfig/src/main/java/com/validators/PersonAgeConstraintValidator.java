package com.validators;

import com.validators.annotations.PersonAgeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonAgeConstraintValidator implements ConstraintValidator<PersonAgeConstraint, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age > 0;
    }
}
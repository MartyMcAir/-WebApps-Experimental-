package com.validators;

import com.model.valueObject.Documents;
import com.validators.annotations.HealthConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HealthConstraintValidator implements ConstraintValidator<HealthConstraint, Documents> {
    @Override
    public boolean isValid(Documents documents, ConstraintValidatorContext constraintValidatorContext) {
        return documents.contains("справка 1");
    }
}
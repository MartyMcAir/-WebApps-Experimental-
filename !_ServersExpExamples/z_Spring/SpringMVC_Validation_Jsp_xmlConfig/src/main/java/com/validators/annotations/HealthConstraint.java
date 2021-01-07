package com.validators.annotations;

import com.validators.HealthConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HealthConstraintValidator.class)
public @interface HealthConstraint {
    String message() default "{health.documents}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
package com.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//@Documented

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleValidator.class)
public @interface TitleAnnotation {
    String message() default "length from 3 to 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
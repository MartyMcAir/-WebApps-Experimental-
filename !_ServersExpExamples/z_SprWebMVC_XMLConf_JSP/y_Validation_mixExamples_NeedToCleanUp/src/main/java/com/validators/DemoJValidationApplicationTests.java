package com.validators;

import com.model.Employee;
import com.model.valueObject.Documents;
import com.validators.annotations.Health;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

// https://habr.com/ru/post/424819/
public class DemoJValidationApplicationTests {
    // Инициализация Validator
    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void testValidators() {
        final Employee person = new Employee("Иван Петров", -4500);

        Set<ConstraintViolation<Employee>> validates = validator.validate(person);
//        Assert.assertTrue(validates.size() > 0);
        assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }

    @Test
    public void healthAndProfessionalValidators() {
        final Employee person = new Employee("Иван Петров", 45);
        person.setHealthDocuments(new Documents(Arrays.asList("справка 1", "справка 3")));
        // person.setProfessionalDocuments(new Documents(Arrays.asList("тест 1", "тест 4")));

        // проверка на здоровье
        Set<ConstraintViolation<Employee>> validates = validator.validate(person, Health.class);
        assertTrue(validates.size() == 0);

        // и если здоровье Ок, то проф. тест
        // validates = validator.validate(person, Professional.class);
        // assertTrue(validates.size() == 0);

        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }
}
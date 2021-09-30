package com.validator;

import com.model.Book;
import com.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.*;

import org.springframework.validation.ValidationUtils;

@Component
public class BookValidator implements Validator {
    @Autowired
    private BookService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "fielName", "404");
        Book obj = (Book) o;
        int id = obj.getId();

        rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (id < 6 || id > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (service.getByID(id) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }
}
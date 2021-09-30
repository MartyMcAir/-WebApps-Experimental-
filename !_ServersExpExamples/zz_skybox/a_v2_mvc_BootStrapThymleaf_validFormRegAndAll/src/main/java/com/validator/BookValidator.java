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
        Book obj = (Book) o;

        if (obj.getAuthor().isEmpty() & obj.getTitle().isEmpty() & obj.getSize() == 0) {
            errors.rejectValue("author", "book.allFields.require1");
            errors.rejectValue("title", "book.allFields.require1");
        }
    }
}
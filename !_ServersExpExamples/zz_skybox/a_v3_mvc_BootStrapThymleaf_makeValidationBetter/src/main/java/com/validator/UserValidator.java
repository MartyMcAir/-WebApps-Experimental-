package com.validator;

import com.model.User;
import com.model.forms.SignupForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

// Сначала простые проверки и последними идут, более сложные и те что больше отбирают performance..
@Component
public class UserValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User obj = (User) o;

        rejectIfEmptyOrWhitespace(errors, "username", "signUpForm.userName.empty");
        rejectIfEmptyOrWhitespace(errors, "email", "signUpForm.email.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "signUpForm.password.empty");
        rejectIfEmptyOrWhitespace(errors, "role", "userForm.role.empty");

        // ............................................................................................

        // UserName
//        if (service.findById(obj.getId()) {
//            errors.rejectValue("username", "signUpForm.userName.duplicate");
//        }

        if ((obj.getUsername().length() < 4) || (obj.getUsername().length() > 20)) {
            errors.rejectValue("username", "signUpForm.userName.longShort");
        }

        // Email
        if (!obj.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "signUpForm.email.invalid");
        }

        if (obj.getEmail().length() > 40) {
            errors.rejectValue("email", "signUpForm.email.longShort");
        }
        //        if (service.findByEmail(obj.getEmail()) {
//            errors.rejectValue("email", "signUpForm.userName.duplicate");
//        }

        // Password
        if ((obj.getPassword().length() < 6) || (obj.getPassword().length() > 20)) {
            errors.rejectValue("password", "signUpForm.password.longShort");
        }

    }

}
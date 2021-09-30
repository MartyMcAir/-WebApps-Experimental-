package com.validator;

import com.model.forms.SignupForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class SignupValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignupForm obj = (SignupForm) o;

        rejectIfEmptyOrWhitespace(errors, "username", "signUpForm.userName.empty");
        rejectIfEmptyOrWhitespace(errors, "email", "signUpForm.email.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "signUpForm.password.empty");
        rejectIfEmptyOrWhitespace(errors, "confirmPassword", "signUpForm.confirmPassword.empty");

        // ............................................................................................

        // UserName
//        if (service.findById(obj.getId()) {
//            errors.rejectValue("username", "signUpForm.userName.duplicate");
//        }

        if ((obj.getUsername().length() < 4) | (obj.getUsername().length() > 20)) {
            errors.rejectValue("username", "signUpForm.userName.longShort");
        }

        // Email
//        if (!obj.getEmail().matches(EMAIL_PATTERN)) {
        if (!EmailValidator.getInstance().isValid(obj.getEmail())) {
            errors.rejectValue("email", "signUpForm.email.invalid");
        }

        if (obj.getEmail().length() > 30) {
            errors.rejectValue("email", "signUpForm.email.longShort");
        }
        //        if (service.findByEmail(obj.getEmail()) {
//            errors.rejectValue("email", "signUpForm.userName.duplicate");
//        }

        // Password
        if (!(obj.getPassword().equals(obj.getConfirmPassword()))) {
            errors.rejectValue("password", "signUpForm.password.invalid");
        }

        if ((obj.getPassword().length() < 6) | (obj.getPassword().length() > 20)) {
            errors.rejectValue("password", "signUpForm.password.longShort");
        }

    }

}
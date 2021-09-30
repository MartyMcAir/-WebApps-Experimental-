package com.validator;

import com.model.forms.LoginForm;
import com.model.forms.SignupForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginForm obj = (LoginForm) o;

        rejectIfEmptyOrWhitespace(errors, "login", "loginForm.login.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "loginForm.password.empty");

        // ............................................................................................

        // Login
//        if (obj.getLogin().isEmpty())
//            errors.rejectValue("login", "loginForm.login.empty");

//        if (!service.findByLogin(obj.getLogin()) {
//            errors.rejectValue("login", "signInForm.login.invalid");
//        }


//        User user = service.getUserByLogin(obj.getLogin());

        // Password
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.encode(user.getPassword()).equals(encoder.encode(obj.getPassword()))) {
//            errors.rejectValue("login", "signInForm.password.invalid");
//        }


    }

}
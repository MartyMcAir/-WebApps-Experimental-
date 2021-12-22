package com.validator;

import com.model.UserHere;
import com.model.forms.LoginForm;
import com.model.forms.SignupForm;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class LoginValidator implements Validator {
    @Autowired
    private UserService service;

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

        Optional<UserHere> userByName = service.getUseByName(obj.getLogin());
        Optional<UserHere> userByEmail = service.getUserByEmail(obj.getLogin());

        Optional<UserHere> user = Optional.empty();
        if (userByName.isPresent()) user = userByName;
        if (userByEmail.isPresent()) user = userByEmail;

        if (!(user.isPresent())) {
            errors.rejectValue("login", "loginForm.login.invalid");
        }

        if (user.isPresent()) {
            if (!(user.get().getPassword().equals(obj.getPassword()))) {
                errors.rejectValue("password", "loginForm.password.invalid");
            }
        }

        // Login

        // Password
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.encode(user.getPassword()).equals(encoder.encode(obj.getPassword()))) {
//            errors.rejectValue("login", "signInForm.password.invalid");
//        }

    }

}
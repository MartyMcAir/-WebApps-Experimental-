package com.controllers;

import com.model.forms.SignupForm;
import com.model.User;
import com.validator.SignupValidator;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SignupValidator signupValidator;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/signIn")
    public String signInPage() {
        return "user/sign_in";
    }

    @PostMapping("/signInRequest")
    public String signInRequest(User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "user/sign_up";
        }
        return "user/sign_up_success";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "user/sign_up";
    }

    @PostMapping("/signUpRequest")
    public String signUpRequest(SignupForm signupForm, BindingResult result) {
        signupValidator.validate(signupForm, result);
        if (result.hasErrors()) {
            return "user/sign_up";
        }
        return "user/sign_up_success";
    }

}
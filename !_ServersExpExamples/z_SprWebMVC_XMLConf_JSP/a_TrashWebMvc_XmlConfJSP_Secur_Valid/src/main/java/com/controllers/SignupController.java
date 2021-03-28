package com.controllers;

import com.model.valueObject.SignupForm;
import com.validators.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// http://www.seostella.com/ru/article/2012/06/20/formy-i-validaciya-form-v-spring.html
// java.lang.ClassNotFoundException: org.apache.oro.text.perl.Perl5Util
@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private SignupValidator signupValidator;

    @GetMapping()
    public String signup(ModelMap model) {
        SignupForm signupForm = new SignupForm();
        model.put("signupForm", signupForm);
        return "sign_in/signup";
    }

    @PostMapping(path = {"/sign_action"})
    public String processSignup(SignupForm signupForm, BindingResult result) {
        signupValidator.validate(signupForm, result);
        if (result.hasErrors()) {
            return "sign_in/signup";
        }
        return "sign_in/signup-success";
    }
}
package com.controllers;

import com.model.forms.LoginForm;
import com.model.forms.SignupForm;
import com.services.UserService;
import com.validator.LoginValidator;
import com.validator.SignupValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final String folderPath = "auth/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private UserService userService;

    @Autowired
    private SignupValidator signupValidator;
    @Autowired
    private LoginValidator loginValidator;

    @GetMapping("/signIn")
    public String signInPage(Model model) {
        model.addAttribute("form", new LoginForm());
        return folderPath + "sign_in";
    }

    @PostMapping("/signInRequest")
    public String signInRequest(@Valid @ModelAttribute("form") LoginForm form, BindingResult result) {
        loginValidator.validate(form, result);
        if (result.hasErrors()) {
            return folderPath + "sign_in";
        }
        return folderPath + "sign_in_success";
    }

    @GetMapping("/signUp")
    public String signUpPage(Model model) {
        model.addAttribute("form", new SignupForm());
        return folderPath + "sign_up";
    }

    @PostMapping("/signUpRequest")
    public String signUpRequest(@Valid @ModelAttribute("form") SignupForm form, BindingResult result) {
        signupValidator.validate(form, result);
        if (result.hasErrors()) {
            return folderPath + "sign_up";
        }

//        userService.save(form.getUserFromForm());
        return folderPath + "sign_up_success";
    }

}
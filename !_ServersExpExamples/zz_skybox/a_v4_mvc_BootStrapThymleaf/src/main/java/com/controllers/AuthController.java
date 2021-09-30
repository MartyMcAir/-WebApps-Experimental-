package com.controllers;

import com.model.forms.LoginForm;
import com.model.forms.SignupForm;
import com.services.AuthService;
import com.services.zExceptionLoadService;
import com.services.UserService;
import com.validator.LoginValidator;
import com.validator.SignupValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final String folderPath = "auth/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SignupValidator signupValidator;
    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private UserService service;
    @Autowired
    private AuthService authService;

//    @Autowired
//    private zExceptionLoadService zExceptionLoadService; // load with err why !? don't know

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

//        authService.save(form.getUserFromForm());
        return folderPath + "sign_up_success";
    }

    // from Trash
    // ............................................................................................................

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();   // After user login successfully.
        System.out.println("User Name: " + userName);
        return "oth/userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else model.addAttribute("msg", "You do not have permission to access this page!");

        return "oth/403Page";
    }

}
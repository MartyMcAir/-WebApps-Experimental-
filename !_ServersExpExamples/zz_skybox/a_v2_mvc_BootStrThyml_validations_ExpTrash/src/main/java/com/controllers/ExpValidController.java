package com.controllers;

import com.model.Book;
import com.model.forms.SignupForm;
import com.validator.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Controller
@RequestMapping("/exp")
public class ExpValidController {
    @Autowired
    private SignupValidator signupValidator;

    // Login and Registration .......
    @GetMapping("/login")
    public String loginPage() {
        return "valid_exp/login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "valid_exp/registration";
    }

    // using annotation is SKIP
    // Valid and Check .......
    // USE annotate in Entity object _ and use OUR @annotation validator
    // and use Message in annotation
    // https://howtodoinjava.com/hibernate/hibernate-validator-java-bean-validation/ - bed example
    //      - can be use with validation.properties..
    // _ JSR 269 - dependency no in Maven
    // http://hibernate.org/validator/tooling/ - docs for Hibernate Validator Annotation Processor
    // docs https://beanvalidation.org/2.0/

    @GetMapping("/valid")
    public String display(Model m) {
        m.addAttribute("obj", new Book());
        return "valid_exp/viewpage";
    }

    // @Valid or Validated !?
    @PostMapping("/check")
//    public String submitForm(@Validated @ModelAttribute("obj") Book e, BindingResult br) {
    public String submitForm(@Valid @ModelAttribute("obj") Book e, BindingResult br) {
        if (br.hasErrors()) {
            return "valid_exp/viewpage";
        } else {
            // save Book and..
            return "valid_exp/final_success";
        }
    }

    // Validation for variable Path
    @Validated
    @GetMapping("/foos/{id}")
    public String findById(@PathVariable @Min(0) final long id) {

        return "valid_exp/index";
    }

    // SignUp .......
    // z_SprWebMVC_XMLConf_JSP\+SecurValidRole_hellokoding _ UserController - unsafe dep _ ERR
    // ... Validate by implements Validator and use ValidationUtils.rejectIfEmptyOrWhitespace
    @GetMapping("/signUpGet")
    public String signupPage(SignupForm signupForm) {
        return "valid_exp/signup";
    }

    @PostMapping("/signUpPost")
    public String processSignup(SignupForm signupForm, BindingResult result) {
        signupValidator.validate(signupForm, result);
        if (result.hasErrors()) {
            return "valid_exp/signup";
        }
        return "valid_exp/signup-success";
    }

    // Users Validation .......
    // ... Validate by userForm and use ValidationUtils.rejectIfEmptyOrWhitespace
    // z_SprWebMVC_XMLConf_Thymleaf\withTrash_Validation_CRUD_Pagin
    @GetMapping("/users")
    public String listUsers(Model model, SignupForm userForm) {
        model.addAttribute("userForm", userForm);
        return "valid_exp/users";
    }

    //    public Object register(SignupForm userForm, BindingResult binding, Model model) {
    @PostMapping("/users")
    public String register(SignupForm userForm, BindingResult binding, Model model) {
//        userForm.validate(binding, userService);
//        userForm.validate(binding);
        if (binding.hasErrors()) return "users";

//        userService.register(new Username(userForm.getUsername()), Password.raw(userForm.getPassword()));
//        RedirectView redirectView = new RedirectView("redirect:/users");
//        redirectView.setPropagateQueryParams(true);
//        return redirectView;
        return "redirect:/exp/users";
    }

}
package com.controllers;

import com.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// https://habr.com/ru/post/424819/
@Controller
@RequestMapping(path = {"/demoEmployee"})
public class DemoJValidationController {
    @Autowired
    @Qualifier("personValidator") // spring validator
    private Validator personValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personValidator);
    }

    @GetMapping
    public String mainPage() {
        return "demoEmployee/index";
    }

    @GetMapping("/editPage")
    public String savePersonAction(ModelMap model) {
        model.addAttribute("person", new Employee());
        return "demoEmployee/personEdit";
    }

    // NPE
    @PostMapping(value = "/save")
    public String savePersonAction(
            @Valid @ModelAttribute("person") Employee person,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "demoEmployee/personEdit"; // to person.jsp page
        }

        model.addAttribute("name", person.getName());
        model.addAttribute("age", person.getAge());
        return "demoEmployee/saveSuccess"; // to saveSuccess.jsp page
    }

}
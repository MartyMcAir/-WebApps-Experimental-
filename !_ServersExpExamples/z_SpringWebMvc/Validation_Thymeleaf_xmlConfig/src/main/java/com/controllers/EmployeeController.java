package com.controllers;

import com.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @RequestMapping("/valid")
    public String display(Model m) {
        m.addAttribute("emp", new Employee());
        return "valid/viewpage";
    }

    @RequestMapping("/check")
    public String submitForm(@Valid @ModelAttribute("emp") Employee e, BindingResult br) {
        if (br.hasErrors()) {
            return "valid/viewpage";
        } else {
            return "valid/final";
        }
    }
}
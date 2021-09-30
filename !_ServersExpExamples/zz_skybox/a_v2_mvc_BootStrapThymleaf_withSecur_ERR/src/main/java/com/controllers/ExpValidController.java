package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exp")
public class ExpValidController {

    @GetMapping("/login")
    public String loginPage(){
        return "valid_exp/logiin";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "valid_exp/logiin";
    }
}
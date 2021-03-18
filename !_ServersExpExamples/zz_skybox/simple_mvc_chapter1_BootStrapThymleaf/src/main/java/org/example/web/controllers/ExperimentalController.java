package org.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExperimentalController {


    @RequestMapping(value = "/exp")
    public String sayHello() {
        return "exp_page";
    }
}
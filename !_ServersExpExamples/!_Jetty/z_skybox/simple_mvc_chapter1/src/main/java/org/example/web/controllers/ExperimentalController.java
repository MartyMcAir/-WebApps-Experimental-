package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExperimentalController {
    private final Logger logger = Logger.getLogger(ExperimentalController.class);

    @RequestMapping(value = "/exp")
    public String sayHello() {
        return "exp_page";
    }
}
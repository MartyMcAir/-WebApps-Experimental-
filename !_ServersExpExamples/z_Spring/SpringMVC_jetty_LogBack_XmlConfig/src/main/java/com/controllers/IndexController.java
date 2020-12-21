package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = {"/"})
public class IndexController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping(path = {"/",})
    public String main() {
        return "index";
    }

    @GetMapping({"/hello"})
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World")
                                String name, Model model) {
        logger.info("url is: appName/hello");
        model.addAttribute("name", name);
        return "index";
    }
}
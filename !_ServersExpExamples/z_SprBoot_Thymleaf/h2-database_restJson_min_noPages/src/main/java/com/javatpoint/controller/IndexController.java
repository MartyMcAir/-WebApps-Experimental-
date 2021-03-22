package com.javatpoint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping(path = {"/",})
    public String main() {
        return "index";
    }

    @GetMapping({"/hello"})
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World")
                                String name, Model model) {
        Object[] arguments = new Object[]{"exception1", "exception2"};
        model.addAttribute("name", name);
        return "index";
    }

}

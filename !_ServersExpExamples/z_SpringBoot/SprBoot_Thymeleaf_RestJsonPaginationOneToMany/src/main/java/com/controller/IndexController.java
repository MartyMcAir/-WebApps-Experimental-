package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // localhost:8080/myContextPath/
    @GetMapping(path = {"/", "/home"})
    public String statPage(Model model) {
        return "index";
    }
}
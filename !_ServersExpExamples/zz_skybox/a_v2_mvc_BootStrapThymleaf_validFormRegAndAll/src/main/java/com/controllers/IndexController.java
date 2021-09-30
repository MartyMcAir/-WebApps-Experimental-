package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = {"/"})
    String startPage(Model model) {
        model.addAttribute("name", "u s er");
        return "index";
    }
}

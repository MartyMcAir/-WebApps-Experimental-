package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = {"/"})
    public String indexPage() {
        return "index.jsp";
    }
}
package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = {"/oauth"})
public class MyOauthController {

    @GetMapping
    public String startPage() {
        return "/my_oauth/index";
    }

    @GetMapping(path = {"/login"})
    public String loginPage(Model model) {
        return "/my_oauth/login_page";
    }
}
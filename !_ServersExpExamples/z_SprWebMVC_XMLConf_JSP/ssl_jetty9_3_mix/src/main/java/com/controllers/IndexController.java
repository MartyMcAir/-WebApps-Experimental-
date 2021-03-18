package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = {"/"})
    String startPage() {
        return "index";
    }

    @GetMapping(path = {"/secure"})
    String securePage() {
        return "secure/secure";
    }

    @GetMapping(path = {"/not_secure"})
    String notSecurePage() {
        return "secure/secure";
    }
}

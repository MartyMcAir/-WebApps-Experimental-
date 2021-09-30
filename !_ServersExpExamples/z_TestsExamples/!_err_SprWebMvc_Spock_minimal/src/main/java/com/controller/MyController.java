package com.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @GetMapping(value = "/message", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String message() {
        return "Hello there!";
    }

}
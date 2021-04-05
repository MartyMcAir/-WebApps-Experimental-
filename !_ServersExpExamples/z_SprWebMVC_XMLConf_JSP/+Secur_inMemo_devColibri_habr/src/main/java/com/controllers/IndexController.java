package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping(path = {"/", "/homepage", "/welcome"})
    public String startPage(ModelAndView modelAndView) {
//        modelAndView.addObject("hello_variable", "hello from IndexController");
        return "index";
    }


    @GetMapping(path = {"/news"})
    public String newsPage() {
        return "habr482/news";
    }

    @GetMapping(path = {"/accessDenied"})
    public String page403() {
        return "habr482/accessDenied_403";
    }
}
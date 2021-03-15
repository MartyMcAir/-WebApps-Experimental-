package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    // localhost:8080/myContextPath/
    @GetMapping(path = {"/", "/home2"})
    public String statPage() {
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/HelloWorld2")
    public ModelAndView firstPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Example");
        model.addObject("message", "This is Hello World!");
        model.setViewName("index");
        System.out.println("° ° ° ° welcomePage running. model = " + model);
        return model;
    }
}
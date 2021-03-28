package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

// here methods from Spring Docs..
@Controller
@RequestMapping("/sprDocs")
public class SpringDocsController {
    @Autowired
    BookService service;
    @Autowired
    LibraryRepository libraryRepository;

    // Эти 4 метода для @RestController не работают!
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greetingForm1(Model model) {
        model.addAttribute("greeting", new Book());
        return "sprDocs/greeting";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greetingSubmit2(@ModelAttribute Book greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "sprDocs/result";
    }

    // ___---___---___---
    @RequestMapping(value = "/greeting2", method = RequestMethod.GET)
    public ModelAndView greetShowForm3(ModelAndView modelAndView) {
        modelAndView.setViewName("sprDocs/greeting");
        modelAndView.addObject("greeting", new Book());
        return modelAndView;
    }

    @RequestMapping(value = "/greeting2", method = RequestMethod.POST)
    public ModelAndView greetSubmit4(@Valid @ModelAttribute("greeting") Book greeting,
                                     BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) return new ModelAndView("error");

        modelAndView.setViewName("sprDocs/result");
        modelAndView.addObject("greeting", greeting);
        return modelAndView;
    }

    // ...........................................................................................................
    @GetMapping("/greetingHello")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "sprDocs/greeting_hello";
    }
}
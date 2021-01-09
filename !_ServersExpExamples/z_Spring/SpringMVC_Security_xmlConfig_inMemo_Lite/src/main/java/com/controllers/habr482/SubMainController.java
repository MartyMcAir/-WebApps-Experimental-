package com.controllers.habr482;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/sub_main"})
public class SubMainController {

    @GetMapping
    public String subPage() {
        return "habr482/index";
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
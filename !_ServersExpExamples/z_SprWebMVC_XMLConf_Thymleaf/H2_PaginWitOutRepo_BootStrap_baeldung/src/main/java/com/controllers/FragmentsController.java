package com.controllers;

import com.utils.StudentUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {
    @GetMapping("/fragments")
    public String getHome() {
        return "oth/frag/fragments";
    }

    @GetMapping("/markup")
    public String markupPage() {
        return "oth/frag/markup";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "oth/frag/params";
    }

    @GetMapping("/other")
    public String otherPage(Model model) {
        model.addAttribute("data", StudentUtils.buildStudents());
        return "oth/other";
    }
}
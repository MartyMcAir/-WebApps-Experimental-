package org.example.web.controllers;

import org.example.web.fake_utils.FakeSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {

        FakeSession.checkSessionAndAddAttribute(model);
        return "index";
    }
}
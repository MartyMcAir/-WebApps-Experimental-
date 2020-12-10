package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.example.web.fake_utils.FakeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/login_fail")
public class LoginFailController {

    private final Logger logger = Logger.getLogger(LoginFailController.class);
    private final LoginService loginService;

    @Autowired
    public LoginFailController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String loginFail(Model model) {
        logger.info("GET /login returns login_page.html");

        model.addAttribute("loginForm", new LoginForm());

        model.addAttribute("FAIL", true);
        FakeSession.checkSessionAndAddAttribute(model);

        return "login_page";
    }
}
package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.example.web.fake_utils.FakeSession;
import org.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("GET /login returns login_page.html");

        model.addAttribute("loginForm", new LoginForm());

        model.addAttribute("FAIL", false);
        FakeSession.checkSessionAndAddAttribute(model);

        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(LoginForm loginFrom, Model model, RedirectAttributes redirectAttributes) {
        if (loginService.authenticate(loginFrom)) {
            logger.info("login OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("login FAIL redirect back to login");
            return "redirect:/login_fail";
        }
    }
}
package com.controllers;

import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping
    public String home(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "userPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {

        userService.save(new User("username_" + new Random().nextInt(1000)));

        return "redirect:/user";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String removeUser(@PathVariable("userId") int userId) {

        userService.delete(userId);

        return "redirect:/user";
    }

}

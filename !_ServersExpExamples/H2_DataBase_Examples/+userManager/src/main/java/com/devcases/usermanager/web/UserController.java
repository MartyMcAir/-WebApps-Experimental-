package com.devcases.usermanager.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devcases.usermanager.model.User;
import com.devcases.usermanager.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {

        userService.save(new User("username_" + new Random().nextInt(1000)));

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String removeUser(@PathVariable("userId") int userId) {

        userService.delete(userId);

        return "redirect:/";
    }

}

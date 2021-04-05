package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// https://habr.com/ru/post/482552/
@Controller
public class AdminController {
//    @Autowired
//    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
//        model.addAttribute("allUsers", userService.allUsers());
        return "habr482/admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
//            userService.deleteUser(userId);
            System.out.println("deleted");
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
        return "habr482/admin";
    }
}

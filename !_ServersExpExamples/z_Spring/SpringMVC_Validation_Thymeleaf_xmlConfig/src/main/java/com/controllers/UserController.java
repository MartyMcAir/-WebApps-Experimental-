package com.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.services.UserService;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping
    public String usersPage(Model model) {
        System.out.println("usersPage");
        model.addAttribute("list", userService.getAllUsers());
        return "user/index_page";
    }

    @GetMapping("/delete")
    public String removeUser(@RequestParam int id) {
        System.out.println("removeUser");
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String newObject(Map<String, Object> model) {
        User user = new User();
        model.put("object", user);
        return "user/new_page";
    }

    @GetMapping(path = {"/edit"})
    public ModelAndView editObject(int id) {
        ModelAndView mav = new ModelAndView("user/edit_page");
        List<User> userList = userService.getAllUsers();
        User userFind = null;
        for (User user : userList) {
            if (user.getId() == id) {
                userFind = user;
                break;
            }
        }
        mav.addObject("object", userFind);
        return mav;
    }

    // detached entity passed to persist: com.devcases.usermanager.model.User
    // new - save + work
    // edit - save = dnt work
    // Field error in object 'object' on field 'id': rejected value []
    // PersistentObjectException: detached entity passed to persist: com.devcases.usermanager.model.User
    @PostMapping(path = {"/save"})
    public String saveObject(@ModelAttribute("object") User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("user/search_page");
        List<User> userList = userService.getAllUsers();

        List<User> userListResult = new ArrayList<>();
        String key = keyword.trim().toLowerCase();

        for (User user : userList) {
            if (user.getUsername().trim().toLowerCase().contains(key))
                userListResult.add(user);
        }
        mav.addObject("list", userListResult);
        return mav;
    }

    //    ............................................
    @GetMapping(value = "/add")
    public String addUser() {
        System.out.println("addUser");
        userService.save(new User("username_" + new Random().nextInt(1000)));
        return "redirect:/user";
    }
}

package com.controllers;

import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

// https://habr.com/ru/post/482552/
@Controller
public class AuthenticateController {

//    @Autowired
//    private UserService userService;

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();

        if (error != null)
            model.addObject("error", "Invalid username and password!");
        if (logout != null)
            model.addObject("msg", "You've been logged out successfully.");

        model.addObject("errorMessge", "my error message from AuthenticateController");
        model.setViewName("habr482/login3");
        return model;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "habr482/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "habr482/registration";
        }
//        if (!userService.saveUser(userForm)) {
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "habr482/registration";
//        }

        return "redirect:/";
    }
}

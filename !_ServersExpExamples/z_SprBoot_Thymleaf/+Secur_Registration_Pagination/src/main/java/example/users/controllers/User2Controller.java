package example.users.controllers;

import example.users.model.Password;
import example.users.model.User;
import example.users.model.UserForm;
import example.users.model.Username;
import example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = {"/users_n"})
public class User2Controller {
    @Autowired
    UserService service;

    // for @GetMapping(path = {"/list"})
    @ModelAttribute("users")
    public Page<User> users(@PageableDefault(size = 5) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping(path = {"/list"})
    public String listUsers(Model model, UserForm userForm) {
        model.addAttribute("userForm", userForm);
        return "users/list";
    }

    @GetMapping(path = {"/registration"})
    public String regPage(UserForm userForm) {
        return "users/registration";
    }

    @PostMapping(path = {"/registration"})
    public Object register(UserForm userForm, BindingResult binding, Model model) {
        userForm.validate(binding, service);
        if (binding.hasErrors()) return "users_n/registration";

        service.register(new Username(userForm.getUsername()), Password.raw(userForm.getPassword()));

        RedirectView redirectView = new RedirectView("/users_n/list");
        redirectView.setPropagateQueryParams(true);
        return redirectView;
    }

    @GetMapping(path = {"/login"})
    public String logPage() {
        return "users/login";
    }

    @PostMapping(path = {"/login"})
    public String login(UserForm userForm, BindingResult binding) {
        userForm.validate(binding, service);
        if (binding.hasErrors()) return "users/registration";

//        service.register(new Username(userForm.getUsername()), Password.raw(userForm.getPassword()));
//        service.chekUser();

        boolean result = true;
        if (result) return "redirect:/home";
        else return "users/login";
    }

    @GetMapping(path = {"/logout"})
    public String logout() {
//        service.logout();
        return "redirect:/home";
    }

    //
    @GetMapping(path = {"/listNonPageable"})
    public String list() {
        return "users/list";
    }
}
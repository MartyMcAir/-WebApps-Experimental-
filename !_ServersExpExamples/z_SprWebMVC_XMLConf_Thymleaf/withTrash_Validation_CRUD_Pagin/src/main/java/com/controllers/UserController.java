/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.controllers;

import com.model.Password;
import com.model.User;
import com.model.UserForm;
import com.model.Username;

import com.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
class UserController {
    private UserService userService;

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model, UserForm userForm) {
        model.addAttribute("userForm", userForm);
        return "user_pages/users";
    }

    @ModelAttribute("users")
    public Page<User> users(@PageableDefault(size = 5) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object register(UserForm userForm, BindingResult binding, Model model) {
        userForm.validate(binding, userService);
        if (binding.hasErrors()) return "users";
        userService.register(new Username(userForm.getUsername()), Password.raw(userForm.getPassword()));
        RedirectView redirectView = new RedirectView("redirect:/users");
        redirectView.setPropagateQueryParams(true);
        return redirectView;
    }
}
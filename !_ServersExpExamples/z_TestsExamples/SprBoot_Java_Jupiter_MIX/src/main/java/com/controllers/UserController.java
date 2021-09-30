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

import java.util.Map;

import com.model.UserForm;
//import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.model.Password;
import com.model.User;
import com.service.UserService;
import com.model.Username;

/**
 * A sample controller implementation to showcase Spring Data web support:
 * <ol>
 * <li>Automatic population of a {@link Pageable} instance as controller method argument. This is achieved by the
 * automatic activation of {@link EnableSpringDataWebSupport} and in turn its registration of a
 * {@link PageableHandlerMethodArgumentResolver}.</li>
 * <li>Usage of proxy-backed interfaces to bind request parameters.</li>
 * </ol>
 *
 * @author Oliver Gierke
 */
@Controller
//@RequiredArgsConstructor
@RequestMapping("/users_origin")
class UserController {

    @Autowired
    private UserService userService;

    /**
     * Equis the model with a {@link Page} of {@link User}s. Spring Data automatically populates the {@link Pageable} from
     * request data according to the setup of {@link PageableHandlerMethodArgumentResolver}. Note how the defaults can be
     * tweaked by using {@link PageableDefault}.
     *
     * @param pageable will never be {@literal null}.
     * @return
     */
    @ModelAttribute("users")
    public Page<User> users(@PageableDefault(size = 5) Pageable pageable) {
        return userService.findAll(pageable);
    }

    /**
     * Registers a new {@link User} for the data provided by the given {@link UserForm}. Note, how an interface is used to
     * bind request parameters.
     *
     * @param userForm the request data bound to the {@link UserForm} instance.
     * @param binding  the result of the binding operation.
     * @param model    the Spring MVC {@link Model}.
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Object register(UserForm userForm, BindingResult binding, Model model) {
        userForm.validate(binding, userService);
        if (binding.hasErrors()) {
            return "users";
        }

        userService.register(new Username(userForm.getUsername()), Password.raw(userForm.getPassword()));

        RedirectView redirectView = new RedirectView("redirect:/users");
        redirectView.setPropagateQueryParams(true);

        return redirectView;
    }

    /**
     * Populates the {@link Model} with the {@link UserForm} automatically created by Spring Data web components. It will
     * create a {@link Map}-backed proxy for the interface.
     *
     * @param model    will never be {@literal null}.
     * @param userForm will never be {@literal null}.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model, UserForm userForm) {
        model.addAttribute("userForm", userForm);
        return "users";
    }

}
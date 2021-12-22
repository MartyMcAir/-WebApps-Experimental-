package com.controllers;

import com.model.UserHere;
import com.model.forms.UserMultiWrapperForm;
import com.services.UserService;
import com.validator.UserMultiWrapperValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final String folderPath = "user/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMultiWrapperValidator userMultiWrapperValidator;
    @Autowired
    private UserService service;

    @GetMapping
    public String startPage() {
        return folderPath + "/index";
    }

    // TODO здесь должен выбираться залогенниный юзер (а не id 1)
    @GetMapping("/profile")
    public String profilePage2(Model model) {
        UserMultiWrapperForm multi2 = new UserMultiWrapperForm();
        UserHere byID = service.getByID(1);
        multi2.setUserOriginBackUp(byID);

        model.addAttribute("form", multi2);
        return folderPath + "upd_profile";
    }

    @PostMapping("/profileUpdate")
    public String profileRequest2(@Valid @ModelAttribute("form") UserMultiWrapperForm form, BindingResult result, Model model) {
        // if user don't change fields
        UserHere newUserHereFromForm = form.getNewUserFromForm();
        if (form.getUserOriginBackUp().equalsWithOutPassword(newUserHereFromForm) & form.isEmptyPasswordFields()) {
            return folderPath + "upd_profile_noChange";
        }

        // if user have error in fields
        userMultiWrapperValidator.validate(form, result);
        if (result.hasErrors()) return folderPath + "upd_profile";

        // if user don't have error in fields
        service.save(form.getNewUserFromFormForSave());
        model.addAttribute("obj", form);
        return folderPath + "upd_profile_success";
    }
}
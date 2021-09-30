package com.controllers;

import com.model.User;
import com.services.UserService;
import com.utils.UserServiceUtils;
import com.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

// TODO продумать логику, что бы при удалении юзера не мог удалиться текущий ADMIN
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final String folderPath = "admin/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;
    @Autowired
    private UserServiceUtils utils;
    @Autowired
    private UserValidator validator;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("form", new User());
        return findPaginated(1, "id", "asc", "10", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, @RequestParam("pageSize") String pageSize,
                                Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new User());

        Page<User> page = service.findPaginated(pageNo, Integer.parseInt(pageSize), sortField, sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", page.getContent());

        return folderPath + "index";
    }

    @GetMapping("/import")
    public String importTestDataFromJsonRequest() {
        // data_users_PiedPiper.json data_users_ruJavaMen.json
        List<User> list = utils.getDataFromJson("json/data_users_ruJavaMen.json");
        for (User obj : list) service.save(obj);

        return "redirect:/admin";
    }

    // New Upd Save Edit ......................................................................................

    @GetMapping("/update/{id}")
    public ModelAndView editRequest(@PathVariable int id) {
        ModelAndView mav = new ModelAndView(folderPath + "upd_form");
        mav.addObject("obj", service.getByID(id));
        return mav;
    }

    @PostMapping(value = "/save")
    public String saveRequest(@Valid @ModelAttribute("obj") User obj, BindingResult result) {
        validator.validate(obj, result);
        if (result.hasErrors()) {
            return folderPath + "upd_form";
        }

        service.save(obj);
        return "redirect:/admin";
    }

    @PostMapping(value = "/saveForm")
    public String saveFormRequest(@ModelAttribute("obj") User obj, @Valid @ModelAttribute("form") User form,
                                  BindingResult result, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new User());

        validator.validate(form, result);
        if (result.hasErrors()) {
            return findPaginated(1, "id", "asc", "10", model);
        }

        service.save(form);
        return "redirect:/admin";
    }

    // DELETE methods ......................................................................................

    @GetMapping("/delete/{id}")
    public String deleteByIdRequest(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delArray")
    public ResponseEntity<String> delByJsonArrayRequest(@RequestBody() List<Integer> list) {
        list.forEach(v -> service.deleteById(v));
        return new ResponseEntity<>("all elements deleted", HttpStatus.OK);
    }

    @GetMapping("/deleteAll")
    public String deleteAllRequest() {
        service.deleteAllData();
        return "redirect:/admin";
    }

    // Search and Filter ......................................................................................

    @GetMapping("/search")
    public String searchRequest(@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
                                @RequestParam("pageSize") String pageSize, @RequestParam("keyword") String keyword,
                                @RequestParam(required = false, name = "regex") String regex, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new User());

        List<User> filteredList;
        if (regex != null) filteredList = utils.getSearchList(keyword, regex);
        else filteredList = service.search(keyword);

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1); // (filteredList.size() / Integer.parseInt(pageSize))
        model.addAttribute("totalItems", filteredList.size());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", filteredList);

        return folderPath + "index";
    }

    @GetMapping("/filter")
    public String filterRequest(
            @RequestParam("idKey") String idKey, @RequestParam("userNameKey") String userNameKey,
            @RequestParam("emailKey") String emailKey, @RequestParam("roleKey") String roleKey,

            @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
            @RequestParam("pageSize") String pageSize,
            @RequestParam(required = false, name = "regex") String regex, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new User());

        List<User> filteredList = utils.getFilteredList(idKey, userNameKey, emailKey, roleKey, regex);

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);
        model.addAttribute("totalItems", filteredList.size());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", filteredList);

        return folderPath + "index";
    }
}
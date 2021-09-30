package com.controllers;

import com.model.Book;
import com.model.forms.LoginForm;
import com.services.BookService;
import com.utils.BookServiceUtils;
import com.validator.BookValidator;
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

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService service;
    @Autowired
    private BookServiceUtils utils;
    @Autowired
    private BookValidator bookValidator;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("form", new Book());
        return findPaginated(1, "id", "asc", "10", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, @RequestParam("pageSize") String pageSize,
                                Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new Book());

        Page<Book> page = service.findPaginated(pageNo, Integer.parseInt(pageSize), sortField, sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", page.getContent());

        return "books/index";
    }

    @GetMapping("/import")
    public String importTestDataFromJsonRequest() {
        List<Book> list = utils.getDataFromJson("data_engAuthor.json");
        for (Book obj : list) service.save(obj);

        return "redirect:/books";
    }

    // New Upd Save Edit ......................................................................................

    @GetMapping("/update/{id}")
    public ModelAndView editRequest(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("books/upd_form");
        mav.addObject("obj", service.getByID(id));
        return mav;
    }

    @PostMapping(value = "/save")
    public String saveRequest(@Valid @ModelAttribute("obj") Book obj, BindingResult result) {
        bookValidator.validate(obj, result);
        if (result.hasErrors()) {
            return "books/upd_form";
        }

        service.save(obj);
        return "redirect:/books";
    }

    @PostMapping(value = "/saveForm")
    public String saveFormRequest(@ModelAttribute("obj") Book obj, @Valid @ModelAttribute("form") Book form,
                                  BindingResult result, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new Book());
        System.out.println("saveFormRequest");

        bookValidator.validate(form, result);
        if (result.hasErrors()) {
            return findPaginated(1, "id", "asc", "10", model);
        }

        service.save(form);
        return "redirect:/books";
    }

    // DELETE methods ......................................................................................

    @GetMapping("/delete/{id}")
    public String deleteByIdRequest(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping(value = "/delArray")
    public ResponseEntity<String> delByJsonArrayRequest(@RequestBody() List<Integer> list) {
        list.forEach(v -> service.deleteById(v));
        return new ResponseEntity<>("all elements deleted", HttpStatus.OK);
    }

    @GetMapping("/deleteAll")
    public String deleteAllRequest() {
        service.deleteAllData();
        return "redirect:/books";
    }

    // Search and Filter ......................................................................................

    @GetMapping("/search")
    public String searchRequest(@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
                                @RequestParam("pageSize") String pageSize, @RequestParam("keyword") String keyword,
                                @RequestParam(required = false, name = "regex") String regex, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new Book());
        System.out.println("searchRequest");

        List<Book> filteredList;
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

        return "books/index";
    }

    @GetMapping("/filter")
    public String filterRequest(
            @RequestParam("idKey") String idKey, @RequestParam("authorKey") String authorKey,
            @RequestParam("titleKey") String titleKey, @RequestParam("sizeKey") String sizeKey,

            @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
            @RequestParam("pageSize") String pageSize,
            @RequestParam(required = false, name = "regex") String regex, Model model) {
        if (model.getAttribute("form") == null) model.addAttribute("form", new Book());
        System.out.println("filterRequest");

        List<Book> filteredList = utils.getFilteredList(idKey, authorKey, titleKey, sizeKey, regex);

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);
        model.addAttribute("totalItems", filteredList.size());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", filteredList);

        return "books/index";
    }
}
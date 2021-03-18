package com.controller;

import com.model.Book;
import com.model.BookForm;
import com.model.Library;
import com.repositories.LibraryRepository;
import com.services.BookService;
import com.utils.DataUtils;
import com.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/listsExamples")
public class ListExamplesPaginationBook_Controller {
    @Autowired
    BookService service;
    @Autowired
    LibraryRepository libraryRepository;

    @GetMapping("/importTestData")
    public String initBooks() {
        // сначала создается библиотека и потом ярлык на неё добавлякется к каждой книге
        // книги без библиотеки не может быть
        Library library = new Library("My Library Name");
        libraryRepository.save(library);

        List<String> list = DataUtils.getListStrings();
        for (String item : list) {
            service.save(new Book(item, library));
        }

        return "redirect:/listsExamples/listVersion_v4";
    }

    // ........................................................................................................
    // LIST VARIOUS
    @ResponseBody
    @GetMapping("listLikeJson")
    public List<Book> listJson() {
        return service.getListAll();
    }

    // https://www.baeldung.com/thymeleaf-in-spring-mvc
    @GetMapping("listInHtml")
    public ModelAndView listHtml() {
        ModelAndView modelAndView = new ModelAndView("listsExamples/listInHtml_v1");
        modelAndView.addObject("list", service.getListAll());
        return modelAndView;
    }

    // ---------------------------
    // using Standard PagingAndSortingRepository SHOW JSON
    @GetMapping("listWithPagination_showJson")
    public ResponseEntity<List<Book>> listPageable_v1(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Book> list = service.getAllForPageable(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // using PagingAndSortingRepository and PageWrapper an JavaScript _ SHOW in HTML TABLE
    @GetMapping(path = {"/listWithPagination_version2"})
    public ModelAndView listPageable_v2(@RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        PageWrapper<Book> pageWrapper = service.getAndLoadUniversalPageWrapper(pageNo, pageSize, sortBy);
//        PageWrapper<Book> pageWrapper = service.getAndLoadDefaultSizeAndSortPage(pageNo);

        ModelAndView modelAndView = new ModelAndView("/listsExamples/listPageableMyJS_v2");
        modelAndView.addObject("list", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    // ... for LIST version 3 and 4
    @ModelAttribute("list")
    public Page<Book> modelForPageable(@PageableDefault(size = 5) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping(path = {"/listByFormValid3"})
    public String listFormValid(Model modelFromMethod_modelForPageable, BookForm formValidation) {
        modelFromMethod_modelForPageable.addAttribute("book", formValidation);
        return "listsExamples/list_FormValidPage_v3";
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping("/listVersion_v4")
    public String listVersion_mayBeBest_v4(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Book> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Book> list = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("list", list);
        return "listsExamples/listPaginationWithSort_v4";
    }

    // ... for LIST version 5

}
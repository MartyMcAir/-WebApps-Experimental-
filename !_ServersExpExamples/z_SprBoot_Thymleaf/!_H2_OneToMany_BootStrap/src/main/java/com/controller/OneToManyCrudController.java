package com.controller;

import com.model.Book;
import com.model.Library;
import com.repositories.LibraryRepository;
import com.services.BookService;
import com.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/oneToMany")
public class OneToManyCrudController {
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
        for (String item : list) service.save(new Book(item, library));

        return "redirect:/listsExamples/listVersion_v4";
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping("/listVersion4_exp")
    public ModelAndView listVersion_mayBeBest_v4(ModelAndView model) {
        return findPaginated2(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public ModelAndView findPaginated2(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir,
                                       ModelAndView model) {
        Page<Book> page = service.findPaginated(pageNo, 5, sortField, sortDir);

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());
        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addObject("list", page.getContent());

        model.setViewName("oneToMany/listVersion_v4");
        return model;
    }
}
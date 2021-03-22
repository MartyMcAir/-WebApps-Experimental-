package com.controller;

import com.model.Book;
import com.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/json_books")
public class BookJsonController {
    @Autowired
    BookService service;

    // В @RestController возвращать String в качестве указания на имя view не выйдет,
    // т.к. в таком случае по данному адресу будет просто строка а не представление
    @GetMapping()
    public ModelAndView indexPg() {
        return new ModelAndView("bookJson/index");
    }
    // ...........................................................................................................

    // работает и в просто @Controller _ возвращает JSON
    @GetMapping("list_PageableResponseEntity")
    public ResponseEntity<Page<Book>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    // ...........................................................................................................
    // Если будет просто @Controller, то ошибка
    // This application has no explicit mapping for /error, so you are seeing this as a fallback
    // Если @RestController, выводит JSON
    @GetMapping("/listBook")
    public List<Book> getListBook() {
        return service.getListAll();
    }

    // По сути тоже самое, но будет работать и в просто @Controller возвращать JSON list
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getAllByResponseBody", headers = "Accept=application/json")
    public List<Book> getAllByRespBody() {
        return service.getListAll();
    }

    // без @ResponseBody в @Controller работать не будет
    @GetMapping(value = "/{id}")
    public Book findById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    // ...........................................................................................................
    // это (3 метода) работают ток в @Controller
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView createSubmit1(@RequestBody Book book) {
//    public ModelAndView createSubmit(Book book) {
        service.save(book);
//        return new ModelAndView("redirect:/json_books");
        return new ModelAndView("bookJson/index");
    }

    // https://www.baeldung.com/spring-resttemplate-post-json
    @PostMapping(value = "/create2", consumes = "application/json", produces = "application/json")
    public Book createPerson2(@RequestBody Book book) {
        service.save(book);
        return book;
    }

    @RequestMapping(value = "/createPage", method = RequestMethod.GET)
    public ModelAndView greetShowForm3(ModelAndView modelAndView) {
        modelAndView.setViewName("bookJson/createPage");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    // ...........................................................................................................
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Book book) {
        if (service.getById(book.getId()) != null)
            service.save(book);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    // ...........................................................................................................
    // ...........................................................................................................
    // ...........................................................................................................
    @ModelAttribute("book")
    public Page<Book> users(@PageableDefault(size = 5) Pageable pageable) {
        return service.findAll(pageable);
    }

    // ...........................................................................................................

    @GetMapping("/list_JsonResponseEntity")
    public ResponseEntity<List<Book>> getJsonBooksRE() {
        return new ResponseEntity<>(service.getListAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/list_JsonResponseBody")
    public ResponseEntity<List<Book>> getJsonBooksRB() {
        return new ResponseEntity<>(service.getListAll(), HttpStatus.OK);
    }
}
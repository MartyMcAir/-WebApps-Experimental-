package com.controller;

import com.model.Foo;
import com.repositories.FooRepository;
import com.services.IFooService;
import com.utils.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

// Err
// https://coderoad.ru/22813188/javax-servlet-ServletException-%D0%BD%D0%B5%D1%82-%D0%B0%D0%B4%D0%B0%D0%BF%D1%82%D0%B5%D1%80%D0%B0-%D0%B4%D0%BB%D1%8F-%D0%BE%D0%B1%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%87%D0%B8%D0%BA%D0%B0
@RestController("/foos")
public class z_FooController {
    @Autowired
    private IFooService service;
    @Autowired
    private FooRepository repo;

    @PostConstruct
    public void init() {
        System.out.println("test");
    }

    // API - read

    @GetMapping("/foos/{id}")
    @ResponseBody
    @Validated
    public Foo findById(@PathVariable @Min(0) final long id) {
        return repo.findById(id)
                .orElse(null);
    }

    @GetMapping
    @ResponseBody
    public List<Foo> findAll() {
        return repo.findAll();
    }

    @GetMapping(params = {"page", "size"})
    @ResponseBody
    @Validated
    public List<Foo> findPaginated(@RequestParam("page") @Min(0) final int page, @Max(100) @RequestParam("size") final int size) {
        return repo.findAll(PageRequest.of(page, size)).getContent();
    }

    // Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception
    // is java.lang.NumberFormatException: For input string: "myExp1"
    @GetMapping(path = {"/myExp1"})
    @ResponseBody
    public List<Foo> findPaginated2() {
        return repo.findAll(PageRequest.of(1, 3)).getContent();
    }

    @RequestMapping(value = "/foo/get", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Foo> findPaginated3(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Foo> resultPage = service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
//            throw new MyResourceNotFoundException();
            System.out.println("MyResourceNotFoundException");
        }
        return resultPage;
    }

    @GetMapping("/pageable")
    public List<Foo> findPaginatedWithPageable(Pageable pageable, final UriComponentsBuilder uriBuilder,
                                               final HttpServletResponse response) {
        final Page<Foo> resultPage = service.findPaginated(pageable);
        if (pageable.getPageNumber() > resultPage.getTotalPages()) {
//            throw new MyResourceNotFoundException();
            System.out.println("MyResourceNotFoundException");
        }
//        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Foo>(Foo.class, uriBuilder, response,
//                pageable.getPageNumber(), resultPage.getTotalPages(), pageable.getPageSize()));

        return resultPage.getContent();
    }

    // API - write

    @PutMapping("/foos/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Foo update(@PathVariable("id") final String id, @RequestBody final Foo foo) {
        return foo;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final Foo resource) {
//        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findById(resource.getId()));
        service.update(resource);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @PostMapping("/foos")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Foo foo) {
        if (null == foo || null == foo.getName()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " 'name' is required");
        }
        repo.save(foo);
    }
}
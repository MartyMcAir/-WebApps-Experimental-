package com.controllers;

import com.exception.RecordNotFoundException;
import com.model.EmployeeEntity;
import com.services.EmployeeService;
import com.utils.EmployeeUtils;
import com.utils.PageWrapper_My;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
@Controller
@RequestMapping(path = {"/emplHowTodo"})
public class EmplHowTodoController {
    @Autowired
    EmployeeService service;
    @Autowired
    private EmployeeUtils utils;

    //................
    @GetMapping(path = {"/list1"})
    public String pageList() {
//        String decodedWord = URLDecoder.decode(word, "UTF-8");
//        ResponseEntity<List<EmployeeEntity>> allEmployees = getAllEmployees(3, 5, "first_name");
        return "list-employees";
    }

    // localhost:8080/list2?pageNo=1&pageSize=5&sortBy=id
    // localhost:8080/list2?pageNo=2&pageSize=5&sortBy=id
    // localhost:8080/list2?pageNo=3&pageSize=5&sortBy=id
    @GetMapping(path = {"/list2"})
    public ModelAndView getAllEmployees2(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "5") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        List<EmployeeEntity> list = service.getAllEmployees(pageNo, pageSize, sortBy);
        ModelAndView modelAndView = new ModelAndView("emplHowTodo/list-employees");
        modelAndView.addObject("employees", list);
        return modelAndView;
    }

    @GetMapping(path = {"/list3"})
    public ModelAndView getAllEmployees3(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        PageWrapper_My<EmployeeEntity> pageWrapper = service.getAndLoadUniversalPageWrapper_My(pageNo, pageSize, sortBy);

        ModelAndView modelAndView = new ModelAndView("emplHowTodo/list-employees");
        modelAndView.addObject("employees", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    @GetMapping(path = {"/list4"})
    public ModelAndView getAllEmployees4(@RequestParam(defaultValue = "0") Integer pageNo) {
        PageWrapper_My<EmployeeEntity> pageWrapper = service.getAndLoadDefaultSizeAndSortPage(pageNo);

        ModelAndView modelAndView = new ModelAndView("/list-employees");
        modelAndView.addObject("employees", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    // ........ Oth..
    @GetMapping(path = {"/edit"})
    public String pageEdit() {
//        ResponseEntity<List<EmployeeEntity>> allEmployees = getAllEmployees(3, 5, "first_name");

        return "emplHowTodo/add-edit-employee";
    }

    // https://forbes.kz/leader/50_bogateyshih_lyudey_mira/ - names get from here
    @GetMapping("/importTestData")
    public String importTestDataFromJson() {
        List<EmployeeEntity> list = utils.getDataFromJson();
        list.add(new EmployeeEntity("Maik Wazovskiy", "lastName", "dsfs@email.com"));

        for (EmployeeEntity obj : list) service.save(obj);

        return "redirect:/emplHowTodo/list3";
    }

    ///......... FROM rest Controller
    @PostMapping
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(EmployeeEntity employee)
            throws RecordNotFoundException {
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);

        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }

    // ...............................................................................................
    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html


}

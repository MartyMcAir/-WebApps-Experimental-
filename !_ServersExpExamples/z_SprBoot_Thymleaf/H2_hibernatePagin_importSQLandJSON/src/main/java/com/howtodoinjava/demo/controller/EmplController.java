package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
import com.howtodoinjava.demo.utils.EmployeeUtils;
import com.howtodoinjava.demo.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmplController {
    @Autowired
    EmployeeService service;
    @Autowired
    private EmployeeUtils utils;

    @GetMapping(path = {"/list1"})
    public String pageList() {
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

        ModelAndView modelAndView = new ModelAndView("/list-employees");
        modelAndView.addObject("employees", list);

        return modelAndView;
    }

    @GetMapping(path = {"/list3"})
    public ModelAndView getAllEmployees3(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        PageWrapper<EmployeeEntity> pageWrapper = service.getAndLoadUniversalPageWrapper(pageNo, pageSize, sortBy);

        ModelAndView modelAndView = new ModelAndView("/list-employees");
        modelAndView.addObject("employees", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    @GetMapping(path = {"/list4"})
    public ModelAndView getAllEmployees4(@RequestParam(defaultValue = "0") Integer pageNo) {
        PageWrapper<EmployeeEntity> pageWrapper = service.getAndLoadDefaultSizeAndSortPage(pageNo);

        ModelAndView modelAndView = new ModelAndView("/list-employees");
        modelAndView.addObject("employees", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    @GetMapping(path = {"/edit"})
    public String pageEdit() {
//        ResponseEntity<List<EmployeeEntity>> allEmployees = getAllEmployees(3, 5, "first_name");

        return "add-edit-employee";
    }

    // https://forbes.kz/leader/50_bogateyshih_lyudey_mira/ - names get from here
    @GetMapping("/importTestData")
    public String importTestDataFromJson() {
        List<EmployeeEntity> list = utils.getDataFromJson();
        list.add(new EmployeeEntity("Maik Wazovskiy", "lastName", "dsfs@email.com"));

        for (EmployeeEntity obj : list) service.save(obj);

        return "redirect:/list3";
    }
}

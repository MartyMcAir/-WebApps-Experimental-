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
@RequestMapping(path = {"/empl"})
public class EmplController {
    @Autowired
    EmployeeService service;
    @Autowired
    private EmployeeUtils utils;

    @GetMapping(path = {"/list3"})
    public ModelAndView getAllEmployees3(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        PageWrapper_My<EmployeeEntity> pageWrapper = service.getAndLoadUniversalPageWrapper_My(pageNo, pageSize, sortBy);

        ModelAndView modelAndView = new ModelAndView("empl/list-employees");
        modelAndView.addObject("employees", pageWrapper.getListContent());
        modelAndView.addObject("totalPages", pageWrapper.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }

    // https://forbes.kz/leader/50_bogateyshih_lyudey_mira/ - names get from here
    @GetMapping("/importTestData")
    public String importTestDataFromJson() {
        List<EmployeeEntity> list = utils.getDataFromJson();
        list.add(new EmployeeEntity("Maik Wazovskiy", "lastName", "dsfs@email.com"));

        for (EmployeeEntity obj : list) service.save(obj);

        return "redirect:/empl/list3";
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


}
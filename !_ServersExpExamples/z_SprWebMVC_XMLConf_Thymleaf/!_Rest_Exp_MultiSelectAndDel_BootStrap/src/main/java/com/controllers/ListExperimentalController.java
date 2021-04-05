package com.controllers;

import com.exception.RecordNotFoundException;
import com.model.EmployeeEntity;
import com.services.EmployeeService;
import com.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/listExp")
public class ListExperimentalController {
    @Autowired
    EmployeeService service;
    @Autowired
    private EmployeeUtils utils;

    @GetMapping("/importTestData")
    public String importTestDataFromJson() {
        List<EmployeeEntity> list = utils.getDataFromJson();
        list.add(new EmployeeEntity("Maik Wazovskiy", "lastName", "dsfs@email.com"));

        for (EmployeeEntity obj : list) service.save(obj);

        return "redirect:/listExp/listVersion4_exp_1";
    }

    @PostMapping("/deleteArrayById")
    public ResponseEntity<String> delArrayById(@RequestBody List<Integer> list) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int id : list) {
                sb.append(id).append(", ");
                service.deleteEmployeeById((long) id);
            }
            System.out.println("deleted id is: " + sb);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Hello World!", HttpStatus.NOT_FOUND);
        }
        // https://www.baeldung.com/spring-response-entity
        return new ResponseEntity<>(" ___ status ok ___ ", HttpStatus.OK);
    }

    @PostMapping("/experimentalGetJsonList")
//    public String experimentPageGetJsonAndShowInConsole2(@RequestBody Map<String, Integer> list) {
    public String experimentPageGetJsonAndShowInConsole2(@RequestBody List<Integer> list) {
        list.forEach(System.out::println);
//        for (Map.Entry<String, Integer> entry : list.entrySet())
//            System.out.println(entry.getValue());

        return "index";
    }

    @PostMapping("/deleteById")
    public String delById(@RequestBody int id) {
        try {
            service.deleteEmployeeById((long) id);
            System.out.println("________________________ issssssssssssssssssss: " + id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return "index";
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping("/listVersion4_exp_0")
    public ModelAndView listVersion_mayBeBest_v4_0(ModelAndView model) {
        return findPaginated0(1, "id", "asc", model);
    }

    @GetMapping("/listVersion4_exp_1")
    public ModelAndView listVersion_mayBeBest_v4_1(ModelAndView model) {
        return findPaginated1(1, "id", "asc", model);
    }

    @GetMapping("/page0/{pageNo}")
    public ModelAndView findPaginated0(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir,
                                       ModelAndView model) {
        Page<EmployeeEntity> page = service.findPaginated(pageNo, 5, sortField, sortDir);

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());
        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addObject("list", page.getContent());

        model.setViewName("listExp/listVersion_v4_0");
        return model;
    }

    @GetMapping("/page/{pageNo}")
    public ModelAndView findPaginated1(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir,
                                       ModelAndView model) {
        Page<EmployeeEntity> page = service.findPaginated(pageNo, 5, sortField, sortDir);

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());
        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addObject("list", page.getContent());

        model.setViewName("listExp/listVersion_v4_1");
        return model;
    }
}
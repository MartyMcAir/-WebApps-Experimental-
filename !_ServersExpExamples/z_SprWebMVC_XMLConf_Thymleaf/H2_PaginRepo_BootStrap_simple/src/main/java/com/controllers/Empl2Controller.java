package com.controllers;


import com.exception.RecordNotFoundException;
import com.model.EmployeeEntity;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
@Controller
@RequestMapping(path = {"/empl2"})
public class Empl2Controller {
    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        EmployeeEntity employee = new EmployeeEntity();
        model.addAttribute("employee", employee);
        return "empl2/new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee) {
        // save employee to database
        employeeService.save(employee);
        return "redirect:/empl2";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get employee from the service
        EmployeeEntity employee = null;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "empl2/update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        // call delete employee method
        try {
            this.employeeService.deleteEmployeeById(id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/empl2";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<EmployeeEntity> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<EmployeeEntity> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "empl2/index";
    }
}
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
@RequestMapping(path = {"/emplJavaGuides"})
public class EmplJavaGuidesController {
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
        return "emplJavaGuides/new_employee";
    }

//    @GetMapping("/showNewEmployeeForm")
//    public ModelAndView showNewEmployeeForm() {
//        ModelAndView mav = new ModelAndView("emplJavaGuides/new_employee");
//        // create mav attribute to bind form data
//        EmployeeEntity employee = new EmployeeEntity();
//        mav.addObject("employee", employee);
//        return mav;
//    }

//    @GetMapping("/new")
//    public String newCustomerForm(Map<String, Object> model) {
//        EmployeeEntity customer = new EmployeeEntity();
//        model.put("object", customer);
//        return "customer/new_page";
//    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee) {
        // save employee to database
        employeeService.save(employee);
        return "redirect:/emplJavaGuides";
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
        return "emplJavaGuides/update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        // call delete employee method
        try {
            this.employeeService.deleteEmployeeById(id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/emplJavaGuides";
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
        return "emplJavaGuides/index";
    }
}
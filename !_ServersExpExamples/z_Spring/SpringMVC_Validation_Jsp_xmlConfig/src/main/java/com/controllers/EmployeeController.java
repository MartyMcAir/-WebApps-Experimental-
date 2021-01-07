package com.controllers;

import com.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = {"/employee"})
public class EmployeeController {

    private static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee("test Employee"));
    }

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("employee", employeeList);

        return "employee/index_List";
    }

    @RequestMapping("/form")
    public String display(Model m) {
        m.addAttribute("emp", new Employee());
        return "employee/form_page";
    }

    @RequestMapping("/save")
    public String submitForm(@Valid @ModelAttribute("emp") Employee e, BindingResult br) {
        if (br.hasErrors()) {
            return "employee/form_page";
//            return "redirect:/form";
        } else {
            employeeList.add(e);
            return "redirect:/employee";
//            return "employee/show_page";
        }
    }
}
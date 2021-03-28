package com.controllers;

import com.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmpController {

    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);

    private Map<Integer, Employee> emps = null;

    //  Error creating bean with name 'empController': Unsatisfied dependency expressed through field 'validator';
//    @Autowired
//    @Qualifier("employeeValidator")
//    private Validator validator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    public EmpController() {
        emps = new HashMap<Integer, Employee>();
    }

    @ModelAttribute("employee")
    public Employee createEmployeeModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Employee();
    }

    @RequestMapping(value = "/emp/save", method = RequestMethod.GET)
    public String saveEmployeePage(Model model) {
        logger.info("Returning empSave.jsp page");
        return "emp/empSave";
    }

    @RequestMapping(value = "/emp/save.do", method = RequestMethod.POST)
    public String saveEmployeeAction(
            @ModelAttribute("employee") @Validated Employee employee,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning empSave.jsp page");
            return "emp/empSave";
        }
        logger.info("Returning empSaveSuccess.jsp page");
        model.addAttribute("emp", employee);
        emps.put(employee.getId(), employee);
        return "emp/empSaveSuccess";
    }
}

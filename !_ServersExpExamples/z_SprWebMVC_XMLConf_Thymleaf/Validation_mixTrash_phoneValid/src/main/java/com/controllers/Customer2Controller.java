package com.controllers;

import com.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/customer2")
public class Customer2Controller {

    private Map<String, Customer> customers = null;

    public Customer2Controller() {
        customers = new HashMap<String, Customer>();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveCustomerPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer2/custSave";
    }

    @RequestMapping(value = "/save_action", method = RequestMethod.POST)
    public String saveCustomerAction(
            @Valid Customer customer,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "customer2/custSave";
        }
        model.addAttribute("customer", customer);
        customers.put(customer.getEmail(), customer);
        return "customer2/custSaveSuccess";
    }
}
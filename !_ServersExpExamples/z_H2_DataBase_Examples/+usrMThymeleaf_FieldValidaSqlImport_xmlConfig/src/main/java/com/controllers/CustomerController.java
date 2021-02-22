package com.controllers;

import java.util.List;
import java.util.Map;

import com.model.Customer;
import com.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView home() {
        List<Customer> listCustomer = customerService.listAll();
        ModelAndView mav = new ModelAndView("customer/index_page");
        mav.addObject("list", listCustomer);
        return mav;
    }

    @GetMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("object", customer);
        return "customer/new_page";
    }

    @PostMapping(value = "/save")
    public String saveCustomer(@ModelAttribute("object") Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("customer/edit_page");
        Customer customer = customerService.get(id);
        mav.addObject("object", customer);

        return mav;
    }

    @GetMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        customerService.delete(id);
        System.out.println("fff");
        return "redirect:/customer";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerService.search(keyword);
        ModelAndView mav = new ModelAndView("customer/search_page");
        mav.addObject("list", result);
        return mav;
    }
}
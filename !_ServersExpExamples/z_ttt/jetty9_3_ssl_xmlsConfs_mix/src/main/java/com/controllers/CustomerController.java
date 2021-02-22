package com.controllers;

import com.model.Customer;
import com.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customerRepository")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView home() {
        // customerService.listAll() - USING by default
        //        List<Customer> listCustomer = customerService.listAll();

        // customerService.getAllCustomersBySQL() - USING by your SQL
        List<Customer> listCustomer = customerService.getAllCustomersBySQL();

        ModelAndView mav = new ModelAndView("customer/index_page");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }

    @GetMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        return "customer/new_page";
    }

    @PostMapping(value = "/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customerRepository";
    }

    @GetMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("customer/edit_page");
        Customer customer = customerService.get(id);
        mav.addObject("customer", customer);

        return mav;
    }

    @GetMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        customerService.delete(id);
        return "redirect:/customerRepository";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerService.search(keyword);
        ModelAndView mav = new ModelAndView("customer/search_page");
        mav.addObject("result", result);

        return mav;
    }
}
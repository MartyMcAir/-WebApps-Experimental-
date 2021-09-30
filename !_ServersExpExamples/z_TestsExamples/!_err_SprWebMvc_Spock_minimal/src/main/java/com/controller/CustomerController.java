package com.controller;

import com.model.Customer;
import com.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.springframework.http.CacheControl.noCache;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final String folderPath = "customer/";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView home() {
        // customerService.listAll() - USING by default
        //        List<Customer> listCustomer = customerService.listAll();

        // customerService.getAllCustomersBySQL() - USING by your SQL
        List<Customer> listCustomer = customerService.getAllCustomersBySQL();

        ModelAndView mav = new ModelAndView(folderPath + "index_page");
        mav.addObject("list", listCustomer);
        return mav;
    }

    @GetMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("obj", customer);
        return folderPath + "new_page";
    }

    @PostMapping(value = "/save")
    public String saveCustomer(@ModelAttribute("obj") Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/update/{id}")
    public ModelAndView editRequest(@PathVariable long id) {
        ModelAndView mav = new ModelAndView(folderPath + "edit_page");
        mav.addObject("obj", customerService.get(id));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteByIdRequest(@PathVariable long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerService.search(keyword);
        ModelAndView mav = new ModelAndView("customer/search_page");
        mav.addObject("list", result);

        return mav;
    }

    // RESTS Methods
    @GetMapping("/products/{id}")
    public ResponseEntity<Customer> getCustomerJSON(@PathVariable String id) {
        return ResponseEntity.ok()
                .cacheControl(noCache())
                .body(new Customer("Cool Gadget", "Looks cool"));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Customer> updateCustomerJSON(@PathVariable String id, @RequestBody Customer product) {
        // update..
        return ResponseEntity.ok(product);
    }
}
package com.controllers;

import com.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public String indexPage() {
        return "product/index";
    }

    // https://developer.mozilla.org/ru/docs/Web/API/Fetch_API/Using_Fetch
    @GetMapping("/createPage")
    public String createJSONfromHtmlFormAnd(Model model) {
        model.addAttribute("obj", new Product());
        return "product/new";
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@RequestBody Product product) {
        // custom logic
        System.out.println(product);
        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("obj", product);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateRequest(Product product) {
        // custom logic
        System.out.println(product);
        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("obj", product);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView jsonHandler(@RequestBody Product product) {
        // custom logic
        System.out.println(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        modelAndView.addObject("obj", product);
        return modelAndView;
    }

    @PostMapping("/save3")
    public ModelAndView jsonHandler3(@RequestBody Product product) {
        // custom logic
        System.out.println(product);
        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("obj", product);
        return modelAndView;
    }

    @PostMapping("/save2")
    @ResponseBody
    public Product jsonHandler2(@RequestBody Product product) {
//        ResponseEntity<Object> responseEntity
        return product;
    }

    @GetMapping("/experimentalCheckBox")
    public String experimentPage() {
        return "product/JSON_experimentCheckBox";
    }

    @GetMapping("/experimentalUseFetch")
    public String expUseFetchPage() {
        return "product/JSON_expUseFetch";
    }

    @GetMapping("/experimentalUseXMLHttpRequest")
    public String expUseXMLHttpReqPage() {
        return "product/JSON_expUseXMLHttpRequest";
    }

    // JSON
    @PostMapping("/experimentalGetJsonString")
    public String experimentPageGetJsonAndShowInConsole(@RequestBody String str) {
        System.out.println(str);
        return "index";
    }

    @PostMapping("/experimentalGetJsonInteger")
    public String experimentPageGetJsonAndShowInConsoleNumber(@RequestBody Integer num) {
        System.out.println("my number: " + num);
        return "index";
    }

    @PostMapping("/experimentalGetJsonList")
    public String experimentPageGetJsonAndShowInConsole2(@RequestBody List<Integer> numbers) {
        numbers.forEach(System.out::println);
        return "index";
    }

    @PostMapping("/experimentalGetJsonObject")
    public String experimentPageGetJsonAndShowInConsole3(@RequestBody Product product) {
        System.out.println(product);
        return "index";
    }

    @PostMapping("/experimentalGetJsonListOfObjects")
    public String experimentPageGetJsonAndShowInConsole4(@RequestBody List<Product> list) {
        list.forEach(System.out::println);
        return "index";
    }
}
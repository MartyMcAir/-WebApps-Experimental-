package com.controllers;

import com.model.Product;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// https://learningprogramming.net/java/spring-mvc/pagination-with-spring-data-jpa-in-spring-mvc/
// HTTP ERROR 500 javax.servlet.ServletException: org.apache.jasper.JasperException
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public String index(HttpServletRequest request, ModelMap modelMap) {
        System.out.println("hello from product Controller");
        List<Product> products = (List<Product>) service.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(products);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "product/index";
    }
}
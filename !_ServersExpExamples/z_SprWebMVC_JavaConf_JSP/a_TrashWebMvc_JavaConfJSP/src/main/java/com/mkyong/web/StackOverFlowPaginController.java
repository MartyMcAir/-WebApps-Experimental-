package com.mkyong.web;

import com.mkyong.model.User;
import com.mkyong.services.UserDetailsServiceImp;
import com.mkyong.utils.FakeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// https://stackoverflow.com/questions/31883643/how-do-i-add-simple-pagination-for-spring-mvc
@Controller
public class StackOverFlowPaginController {


    @Autowired
    UserDetailsServiceImp service;

    @GetMapping("stackOverFlowPagin")
    public String startPage() {
        return "stackoverflowPagination/index";
    }

    @RequestMapping(value = "/list")
    public ModelAndView listOfUsers(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("list-of-users");

//        List<User> users = userService.getUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(FakeBase.getUsersList());
        pagedListHolder.setPageSize(5);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        modelAndView.addObject("page", page);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }

        return modelAndView;
    }
}

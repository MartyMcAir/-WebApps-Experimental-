package com.mkyong.web;

import com.mkyong.dao.UserDao;
import com.mkyong.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// Пакет web должен быть
// Если контроллер перенести в package controllers _ то не заработает

@Controller
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {

        logger.debug("mkyong");

        //User user = userDao.findByName("mkyong");

        List<User> users = userDao.findAll();

        System.out.println(users);

        model.addAttribute("user", users);

        return "welcome";

    }

}
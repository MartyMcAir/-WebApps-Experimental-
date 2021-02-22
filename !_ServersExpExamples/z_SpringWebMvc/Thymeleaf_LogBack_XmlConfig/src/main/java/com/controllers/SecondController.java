package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Controller
public class SecondController {
    private final Logger logger = LoggerFactory.getLogger(SecondController.class);

    @GetMapping(path = {"/secondHello"})
    public String secondHello() {
        Object[] arguments = new Object[]{"exception1", "exception2"};
        logger.info(" --- INFO second controller", arguments);
        logger.debug(" --- DEBUG second controller");
        logger.error(" --- ERROR second controller");
        return "index";
    }
}
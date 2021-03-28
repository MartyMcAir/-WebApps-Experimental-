package com.ids.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Пакет web должен быть
// Если контроллер перенести в package controllers (на уровень config пакета)
// _ то не заработает
@Controller
public class OneController {
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String hello() {
        return "one";
    }
}
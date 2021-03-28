package com.ids.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Пакет web должен быть
// Если контроллер перенести в package controllers (на уровень config пакета)
// _ то не заработает
@Controller
public class BaseController {
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return "index";
    }
}
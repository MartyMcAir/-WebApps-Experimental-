package webApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // при наборе пользователем в браузере url website/hello-world
    // он попадет в данный метод
    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world"; // возвращает строку - имя представления для юзера

    }
}
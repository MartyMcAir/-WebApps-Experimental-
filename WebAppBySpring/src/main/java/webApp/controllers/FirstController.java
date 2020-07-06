package webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// alishev
// https://www.youtube.com/watch?v=JHTqKQgrVKE&list=PLAma_mKffTOR5o0WNHnY0mTjKxnCgSXrZ&index=17
@Controller   // указывем Spring, что данный класс явл контроллером
@RequestMapping("/first") // указываем префикс для всех адресов
public class FirstController {
    // считается хорошей практикой хранить вьюхи относящиеся к каждому отд. контроллеру
    // в папке с его именем т.е. в этом случа в папке first

    // обычный адрес: siteName/hello
    // с префиксом: siteName/first/hello
    @GetMapping("/hello")
    public String helloPage() {
        return "first/hello";
    }

    // обычный адрес: siteName/goodbye
    // с префиксом: siteName/first/goodbye
    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}

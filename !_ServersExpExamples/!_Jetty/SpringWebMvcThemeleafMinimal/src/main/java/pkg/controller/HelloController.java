package pkg.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {

        var words = List.of("mountain OS", "noon", "rock", "river", "spring");

        model.addAttribute("words", words);

        return "hello";
    }
}
package pkg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pkg.utils.FakeSession;

@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = {"/", "/simple_mvc/"}, produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {
        logger.info("index page opened");

        FakeSession.checkSessionAndAddAttribute(model);
        return "index";
    }
}
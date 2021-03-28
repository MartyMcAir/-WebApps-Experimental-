package pkg.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pkg.utils.FakeSession;


@Controller
@RequestMapping
public class LoginController {

    @GetMapping(value = {"/login"})
    public String login(Model model) {
        model.addAttribute("FAIL", false);
        FakeSession.checkSessionAndAddAttribute(model);

        return "login_page";
    }

    @PostMapping(value = {"/logged_complete"})
    public String auth(Model model) {
        model.addAttribute("FAIL", false);
        FakeSession.checkSessionAndAddAttribute(model);

        return "loggedComplete";
    }

    @GetMapping(value = {"/logout"})
    public String logOut(Model model) {
        model.addAttribute("FAIL", false);
        FakeSession.checkSessionAndAddAttribute(model);
        return "login_page";
    }
}
package webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForthModelParamController {

    @GetMapping("/dataFromModelToView")
    public String dataFromModelToView(HttpServletRequest request, Model model) {
        String firstParam = request.getParameter("firstParam");
        String secondParam = request.getParameter("secondParam");

        System.out.println("ForthModelParamController method: dataFromModelToView().. ");
        System.out.println("your firstParam: " + firstParam + ", and secondParam: " + secondParam);

        // передаем параметры в модель, что бы представление потом мог вытащить эти значения из модели
        model.addAttribute("firstParam", firstParam);
        model.addAttribute("secondParam", secondParam);

        return "forthModel/dataFromModelToView";
    }
}
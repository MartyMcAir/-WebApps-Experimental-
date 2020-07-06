package webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/calculator") // ставим для всех страниц контроллера префикс /calculator
public class CalculatorController {

    @GetMapping("/calculate")
    public String calculate(HttpServletRequest request, Model model) {
        String firstParam = request.getParameter("firstParam");
        String secondParam = request.getParameter("secondParam");

        String res = "nothing, dont have params..";
        if (firstParam != null & secondParam != null) {
            try {
                double a = Integer.parseInt(firstParam);
                double b = Integer.parseInt(secondParam);
                String operationName = request.getParameter("operationName");

                switch (operationName) {   // перебираем возможные операции
                    case "multiply":
                        res = a + " * " + b + " =" + multiply(a, b);
                        break;
                    case "division":
                        res = a + " / " + b + " =" + division(a, b);
                        break;
                }
//                if (operationName.equals("multiply")) res = a + " * " + b + " =" + multiply(a, b);
//                if (operationName.equals("division")) res = a + " / " + b + " =" + division(a, b);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                res = "your param is not number!";
            }
        }
        // передаем результ калькуляций в модель, чтобы представление вытащило по ключу "result"
        model.addAttribute("result", res);
        return "calculator/calculate";
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        return a / b;
    }
}
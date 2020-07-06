package webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ThirdParamController {
    //  найболее гибкий метол получения параметров запроса от юзера и получени др. инфы о запросе
    @GetMapping("/getParamByServletReq")
    public String getParamByServletReq(HttpServletRequest request) {
        String param1 = request.getParameter("firstParam");
        String param2 = request.getParameter("secondParam");

        System.out.println("ThirdParamController method: getParamByServletReq().. ");
        System.out.println("your firstParam: " + param1 + ", and secondParam: " + param2);
        return "thirdParam/getParamByServletReq";
    }

    @GetMapping("/getParamByAnnotationReq")
    // При отсутствии передаваемых апарметров в строке запроса будет:
    // Exception Required String parameter 'firstParam' is not present
    // а при использовании HttpServletRequest - всё ок
//    public String getParamByAnnotationReq(@RequestParam("firstParam") String firstParam, @RequestParam("secondParam") String secondParam) {

    // тогда в параметрах анностации необходимо указывать - required = false
    public String getParamByAnnotationReq(@RequestParam(value = "firstParam", required = false) String firstParam,
                                          @RequestParam(value = "secondParam", required = false) String secondParam) {
        System.out.println("ThirdParamController method: getParamByAnnotationReq().. ");
        System.out.println("your firstParam: " + firstParam + ", and secondParam: " + secondParam);

        return "thirdParam/getParamByServletReq";
    }
}

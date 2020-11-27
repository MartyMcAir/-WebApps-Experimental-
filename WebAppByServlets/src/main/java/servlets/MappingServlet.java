package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/MappingServletPathFrom@WebServlet/id/*", "/MappingServletAlternatePathFrom@WebServlet"})
public class MappingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/simpleJSP/mappingExamplePage.jsp");

        // https://www.youtube.com/watch?v=Ic-kWTx0VqU&list=PLU2ftbIeotGoQGD51e0qb98lE0xhgNDF1&index=2
        final String requestURI = req.getRequestURI();
        System.out.println(requestURI);

        final Map<String, String[]> parameterMap = req.getParameterMap();
        final String params = formatParams(parameterMap);
//        resp.getWriter().write(params);
        req.setAttribute("params", params);

        requestDispatcher.forward(req, resp);
    }

    private String formatParams(Map<String, String[]> parameterMap) {
        return parameterMap.entrySet()
                .stream()
                .map(entry -> {
                    String param = String.join(" ant ", entry.getValue());
                    return entry.getKey() + " => " + param;
                })
                .collect(Collectors.joining("/n"));
    }
}
package servlets.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCookiesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем все куки клиента (предназначенные нашему сайту _ домену)
        Cookie[] cookies = req.getCookies();

        PrintWriter printWriter = resp.getWriter();

        printWriter.println("<html>");

        // выводим ключ - значение в браузер
        for (Cookie cookie : cookies)
            printWriter.println("<p> Cookie name: " + cookie.getName() + ", value: " + cookie.getValue() + "</p>");

        printWriter.println("<a href=\".\">come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}

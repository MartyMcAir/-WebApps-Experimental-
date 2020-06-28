package servlets.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SetCookiesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("some_id", "123");
        Cookie cookie2 = new Cookie("some_name", "Tom");

        // назначаем срок жизни в браузере юзера _ обязат параметр
        cookie1.setMaxAge(24*60*60); // 24 часа
        cookie2.setMaxAge(24*60*60);

        // передаем куки юзеру
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        printWriter.println("<a href=\".\">come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}

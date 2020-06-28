package servlets.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteCookiesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // some_id - ключ должен совпадать с тем куки что хотим удалить
        Cookie cookie = new Cookie("some_id", "");
        cookie.setMaxAge(0);  // таки образом браузер клиента сам удалит т.к. время хранения 0 сек
//        cookie.setMaxAge(-1); // а в этом случае куки удаляться тогда, когда браузер юзера закроется

        resp.addCookie(cookie);

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        printWriter.println("<a href=\".\">come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}

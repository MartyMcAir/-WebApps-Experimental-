package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        // чтоб русск яз норм. выводился указываем кодировку
//        printWriter.println("<%@ tag pageEncoding=\"utf-8\" language=\"java\" %>");
        printWriter.println("<head><meta charset=\"utf-8\"></head>");

        // Получаем сессию
        HttpSession session = req.getSession();

        // Атрибут для каждого отдельного пользователя _ и это уже будет персонализированно
        Integer count = (Integer) session.getAttribute("count");
        // если null т.е. юзе зашел первый раз тогда присваиваем 1, иначе значение +1
        if (count == null) {
            session.setAttribute("count", 1);
            count = 1;
        } else session.setAttribute("count", count + 1);


        printWriter.println("<h1>Your Count is: " + count + "</h1>");

        printWriter.println("<a href=\".\">come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}

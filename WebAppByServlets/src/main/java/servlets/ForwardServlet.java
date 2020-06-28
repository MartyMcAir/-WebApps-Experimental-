package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ForwardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметры запроса name
        String name = req.getParameter("name");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");

        // чтоб русск яз норм. выводился указываем кодировку
//        printWriter.println("<%@ tag pageEncoding=\"utf-8\" language=\"java\" %>");
        printWriter.println("<head><meta charset=\"utf-8\"></head>");


        // Сам редирект в Google _ та же команда что и для Forward
//        resp.sendRedirect("https://www.google.com");

//        resp.sendRedirect("./hello-secondJsp");
        resp.sendRedirect("./simpleJSP/secondJsp.jsp");

//        printWriter.println("</html>");
    }
}

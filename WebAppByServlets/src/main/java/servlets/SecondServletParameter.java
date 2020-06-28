package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecondServletParameter extends HttpServlet {
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
        printWriter.println("<h1>Hello this page from class SecondServletParameter _ method doGet(..) </h1>");
        printWriter.println("<h1>And Hello your name parameter is: " + name + "</h1>");
        printWriter.println("<a href=\".\">Назад на главную _ come Back to Main Page</a>");

        printWriter.println("<form action=\"./hello-SecondServletParameter?name=" + name + "\">\n" +
                "    Name Parameter is..: <input type=\"text\" name=\"name\"><br>\n" +
                "    <button type=\"submit\">Submit Name Parameter</button>\n" +
                "</form>");

        printWriter.println("</html>");
    }
}

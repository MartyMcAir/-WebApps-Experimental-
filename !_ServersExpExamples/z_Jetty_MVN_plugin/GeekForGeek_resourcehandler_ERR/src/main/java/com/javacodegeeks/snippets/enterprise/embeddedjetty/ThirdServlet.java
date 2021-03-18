package com.javacodegeeks.snippets.enterprise.embeddedjetty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/thirdServlet"})
public class ThirdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        // чтоб русск яз норм. выводился указываем кодировку
//        printWriter.println("<%@ tag pageEncoding=\"utf-8\" language=\"java\" %>");
        printWriter.println("<head><meta charset=\"utf-8\"></head>");
        printWriter.println("<h1>Hello this page from class ThirdServlet _ method doGet(..) </h1>");
        printWriter.println("<a href=\".\">Назад на главную _ come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}
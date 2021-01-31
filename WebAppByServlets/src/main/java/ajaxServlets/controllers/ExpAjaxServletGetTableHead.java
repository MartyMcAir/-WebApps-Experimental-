package ajaxServlets.controllers;

import util.SQL_Operations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExpAjaxServletGetTableHead extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQL_Operations sql_operations = new SQL_Operations();
        String getWhat = req.getParameter("get_what");
        resp.setCharacterEncoding("UTF-8"); // при JSON обязательный параметр
        resp.setContentType("text/plain"); // а иначе в браузере Firefox синтаксич ошибка..

//        System.out.println("ExperimentalAjaxServlet2 getWhat from doGet: " + getWhat);
        System.out.println("ExpAjaxServletGetTableHead getWhat from doGet: " + getWhat);

        try (PrintWriter writer = resp.getWriter()) {
            StringBuilder sb = new StringBuilder();
            if (getWhat.equals("row_names")) {
                List<String> books = sql_operations.getAllColumnsNamesFromTable("books");
                for (String item : books) sb.append(item).append("_");
            }

            String str = sb.toString();
            writer.println(str.substring(0, str.lastIndexOf("_")));
        }

        // но если нажать на кнопку из <form..>, то вместо страницы будет текст
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ajaxPages/ajaxGet.jsp");
//        requestDispatcher.forward(req, resp);
    }
}
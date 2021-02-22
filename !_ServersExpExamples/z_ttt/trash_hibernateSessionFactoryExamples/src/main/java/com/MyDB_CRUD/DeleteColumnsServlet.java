package com.MyDB_CRUD;


import com.utils.SQL_Operations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteColumnsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String columnName = req.getParameter("columnName");
        SQL_Operations sql_operations = new SQL_Operations();
        int res = sql_operations.deleteColumnsByLabel(columnName);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/MyDbPages/AllinOneCRUD.jsp");
        requestDispatcher.forward(req, resp);
    }
}

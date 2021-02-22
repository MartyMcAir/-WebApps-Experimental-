package com.MyDB_CRUD;


import com.utils.SQL_Operations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxDeleteOneRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String columnName = req.getParameter("columnID");
        SQL_Operations sql_operations = new SQL_Operations();

        String columnID = req.getParameter("columnID");
        int res = sql_operations.deleteOneRowById(Integer.parseInt(columnID));

        if (res > 0) {
            PrintWriter writer = resp.getWriter();
            writer.print("operating in success");
            System.out.println("AjaxDeleteOneRowServlet: operating in success, deleted columnID is: " + columnID);
            resp.setStatus(200);
        } else resp.setStatus(501);
    }
}

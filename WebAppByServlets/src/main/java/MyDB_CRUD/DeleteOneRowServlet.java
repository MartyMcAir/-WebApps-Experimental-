package MyDB_CRUD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOneRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String columnName = req.getParameter("columnID");
        SQL_Operations sql_operations = new SQL_Operations();

        String columnID = req.getParameter("columnID");
        int queryResult = sql_operations.deleteOneRowById(Integer.parseInt(columnID));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/MyDbPages/AllinOneCRUD.jsp");
        requestDispatcher.forward(req, resp);
    }
}

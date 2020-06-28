package MyDB_CRUD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQL_Operations sql_operations = new SQL_Operations();
        List<String> books = sql_operations.getAllColumnsNamesFromTable("books");


        List<String> parameters = new ArrayList<>();
        for (String item : books) {
            String parameter = req.getParameter(item);
            parameters.add(parameter);
        }

        // сложность во вставке типа данных т.е. вставка идёт текста по дефолту
        // а если тип во вставляемое поле numeric т.е. перед вставкой надо как то определять
        // тип поля куда вставляется и преоразовывать, либо при вставке сразу помечать как тип int -
        // и уже перед входом не давать вставить туда текст.. при этом преобразователь
        // списка в строку должен понимать, на какой позиции какой нужен тип данных..
        int res = sql_operations.addDataInTable("books", parameters);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/MyDbPages/AllinOneCRUD.jsp");
        requestDispatcher.forward(req, resp);
    }
}

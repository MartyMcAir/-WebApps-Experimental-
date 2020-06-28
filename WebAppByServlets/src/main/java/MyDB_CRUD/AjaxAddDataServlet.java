package MyDB_CRUD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjaxAddDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQL_Operations sql_operations = new SQL_Operations();
        List<String> books = sql_operations.getAllColumnsNamesFromTable("books");

        List<String> parameters = new ArrayList<>();
        for (String item : books) {
            String parameter = req.getParameter(item);
//            System.out.println(item + " " + parameter);
            parameters.add(parameter);
        }

        // Функция PostgreSQL для последнего вставленного идентификатора
        // https://overcoder.net/q/9591/%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D1%8F-postgresql-%D0%B4%D0%BB%D1%8F-%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BD%D0%B5%D0%B3%D0%BE-%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B8%D0%B4%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%82%D0%BE%D1%80%D0%B0

        // сложность во вставке типа данных т.е. вставка идёт текста по дефолту
        // а если тип во вставляемое поле numeric т.е. перед вставкой надо как то определять
        // тип поля куда вставляется и преоразовывать, либо при вставке сразу помечать как тип int -
        // и уже перед входом не давать вставить туда текст.. при этом преобразователь
        // списка в строку должен понимать, на какой позиции какой нужен тип данных..

        int res = sql_operations.addDataInTable("books", parameters);
        if (res > 0) {
            String maxIdIs = sql_operations.getMaxIdFrom("books");
            PrintWriter writer = resp.getWriter();
            writer.print(maxIdIs);
            System.out.println("AjaxAddDataServlet: operating in success, added newID is: " + maxIdIs);
            resp.setStatus(200);
        } else resp.setStatus(501);


        // если юзать executeQuery для получения ResultSet а из него id
        // , то Exception: PSQLException: Запрос не вернул результатов.
//        ResultSet res = sql_operations.ajaxAddDataInTable("books", parameters);
//        try (PrintWriter writer = resp.getWriter()) {
//            writer.print(res.getString("id"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

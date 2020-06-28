package MyDB_CRUD;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");

        try {
            Class.forName("org.postgresql.Driver"); // подключаемся к нашему драйверу _ PostGreSQL

            // создаем подключение к БД
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "Z33_q77_888");

            // Объект для совершения запросов к БД
            Statement statement = connection.createStatement();

            String query = "SELECT title from books";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
                printWriter.println(resultSet.getString("title")); // выводим названия книг

            statement.close(); // закрываем подключение к БД
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        printWriter.println("<a href=\".\">Назад на главную _ come Back to Main Page</a>");
        printWriter.println("</html>");
    }
}

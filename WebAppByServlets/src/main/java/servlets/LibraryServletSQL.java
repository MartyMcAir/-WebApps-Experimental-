package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class LibraryServletSQL extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        myEXP(resp);

//        fromAlishevJavaEE(resp);
//        fromPostGreDocs();

        printWriter.println("<a href=\".\">Назад на главную _ come Back to Main Page</a>");
        printWriter.println("</html>");
    }

    private void myEXP(HttpServletResponse response) {
        try {
//            Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());

            // создаем подключение к БД
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydb", "postgres", "Z33_q77_888");

            // Объект для совершения запросов к БД
            Statement statement = connection.createStatement();
            String query = "SELECT title from books";
            ResultSet resultSet = statement.executeQuery(query);

            PrintWriter writer = response.getWriter();

            // выводим названия книг
            while (resultSet.next()) {
                String string = resultSet.getString("title");
                writer.println(string);
                System.out.println(string);
            }

            statement.close(); // закрываем подключение к БД
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void fromPostGreDocs() {
        // https://jdbc.postgresql.org/documentation/head/connect.html
        try {
            String url = "jdbc:postgresql://localhost/db";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "Z33_q77_888");
//            props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection(url, props);

            // OR
//            String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
//            Connection conn = DriverManager.getConnection(url);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void fromAlishevJavaEE(HttpServletResponse resp) throws IOException {
        // подключаемся к нашему драйверу _ PostGreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = resp.getWriter();
        try {
            // создаем подключение к БД
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "Z33_q77_888");

            // Объект для совершения запросов к БД
            Statement statement = connection.createStatement();
            String query = "SELECT title from books";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String string = resultSet.getString("title");
                printWriter.println(string); // выводим названия книг
                System.out.println(string);
            }

            statement.close(); // закрываем подключение к БД
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

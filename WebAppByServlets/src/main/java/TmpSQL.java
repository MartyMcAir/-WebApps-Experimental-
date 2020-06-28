import java.io.PrintWriter;
import java.sql.*;

public class TmpSQL {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            // создаем подключение к БД
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydb", "postgres", "Z33_q77_888");

            // Объект для совершения запросов к БД
            Statement statement = connection.createStatement();
            String query = "SELECT title from books";
            ResultSet resultSet = statement.executeQuery(query);

            // выводим названия книг
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title"));
            }

            statement.close(); // закрываем подключение к БД
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

//        fromAlishev();
    }

    private static void fromAlishev() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // создаем подключение к БД
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydb", "postgres", "Z33_q77_888");

            // Объект для совершения запросов к БД
            Statement statement = connection.createStatement();
            String query = "SELECT title from books";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("title")); // выводим названия книг
            }

            statement.close(); // закрываем подключение к БД
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


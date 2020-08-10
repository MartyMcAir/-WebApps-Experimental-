package MyDB_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DBConnectionPoolVector {   // https://habr.com/ru/post/229199/
    // Set - быстрее Vector
    private Vector<Connection> connectionList = new Vector<>();
    private Vector<Connection> usedConnectionList = new Vector<>();
    private final String DB_Driver = "org.postgresql.Driver";
    private final String DB_Full_Name = "jdbc:postgresql://localhost:5432/postgres";
//    private final String DB_Full_Name = "jdbc:postgresql://localhost:5432/postgres";
    private final String userName = "postgres";
    private final String DB_password = "Z33_q77_888";

    public DBConnectionPoolVector(int connectionCount) {   // создание подключений в кол-ве connectionCount
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < connectionCount; i++) {
            connectionList.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_Full_Name, userName, DB_password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public Connection retrieve() {   // метод получения подключения к БД
        Connection newConnection = null;
        if (connectionList.isEmpty()) newConnection = getConnection();
        else {
            newConnection = connectionList.lastElement();
            connectionList.removeElement(newConnection);
        }
        usedConnectionList.add(newConnection);
        return newConnection;
    }

    public void putBackConnection(Connection c) {   // возвращаем коннект назад в пул, если больше не нужен
        if (c != null) {
            synchronized (this) {
                if (usedConnectionList.removeElement(c)) connectionList.add(c);
                else throw new NullPointerException("Connection not in the usedConnectionList");
            }
        }
    }

    public int getAvailableConnection() {
        return connectionList.size();
    }

    public int getUsedConnection() {
        return usedConnectionList.size();
    }
}

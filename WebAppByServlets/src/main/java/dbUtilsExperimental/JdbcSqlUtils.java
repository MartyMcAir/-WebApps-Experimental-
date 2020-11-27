package dbUtilsExperimental;

import MyDB_CRUD.DBConnectionPoolVector;

import java.sql.*;

public class JdbcSqlUtils {
    private final Connection connection;

    public JdbcSqlUtils() {
        DBConnectionPoolVector dbConnectionPoolVector = new DBConnectionPoolVector(3);
        this.connection = dbConnectionPoolVector.retrieve();
    }

    public String getMaxIdFrom(String tableName) {
        String res = null;
        String sql1 = "SELECT MAX(studentId) AS max_id FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = String.valueOf(resultSet.getInt("max_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // jdbcSqlUtils.getMin("age", "Student");
    // PSQLException: Bad value for type int : age
    // поменял на String теперь тупо возвращает fieldName
    public String getMinErr(String fieldName, String tableName) {
        String res = null;
//        String sql1 = "SELECT min(" + fieldName + ") AS resIs FROM " + tableName;
        String sql1 = "SELECT min(" + "?" + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setString(1, fieldName);
            ResultSet resultSet = statement.executeQuery();
            // ResultSet not positioned properly, perhaps you need to call next.
            //            res = resultSet.getInt("resIs");
//            if (resultSet.next()) res = resultSet.getString("resIs");
            if (resultSet.next()) res = resultSet.getString("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Aggregate Functions
    public int getMin(String fieldName, String tableName) {
        int res = -1;
//        String sql1 = "SELECT min(" + fieldName + ") AS resIs FROM " + tableName;
        String sql1 = "SELECT min(" + fieldName + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getInt("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getMax(String fieldName, String tableName) {
        int res = -1;
        String sql1 = "SELECT max(" + fieldName + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getInt("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public long getSum(String fieldName, String tableName) {
        long res = -1;
        String sql1 = "SELECT sum(" + fieldName + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getLong("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public double getAvg(String fieldName, String tableName) {
        double res = -1;
        String sql1 = "SELECT avg(" + fieldName + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getDouble("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public long getCount(String fieldName, String tableName) {
        long res = -1;
        String sql1 = "SELECT count(" + fieldName + ") AS resIs FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getLong("resIs");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
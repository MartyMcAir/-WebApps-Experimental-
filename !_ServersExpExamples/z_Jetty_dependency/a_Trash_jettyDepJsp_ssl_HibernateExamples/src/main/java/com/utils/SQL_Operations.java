package com.utils;

import com.jdbc.DBConnectionPoolVector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL_Operations {
    private final Connection connection;

    public SQL_Operations() {
        DBConnectionPoolVector dbConnectionPoolVector = new DBConnectionPoolVector(3);
        this.connection = dbConnectionPoolVector.retrieve();


        try (Statement statement = connection.createStatement();) {
            DBConnectionPoolVector conPool = new DBConnectionPoolVector(3);
            Connection connection = conPool.retrieve(); // using DriverManager.getConnection(..

            String sql_tblBooks = "CREATE TABLE IF NOT EXISTS books( " +
                    "    Id serial PRIMARY KEY );";

            statement.execute(sql_tblBooks);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addToListAllColumns(String label, List<String> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) list.add(resultSet.getString(label));
    }

    private void addToListAllElements(List<String> list, ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) list.add(rsmd.getColumnName(i));
    }

    private String listToStringForKey(List<String> books) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < books.size(); i++)           // поле id пропускаем
            sb.append(books.get(i)).append(", ");

        String s = sb.toString();
        int index = s.lastIndexOf(",");
        return s.substring(0, index); // обрезаем в конце запятую
    }

    private String listToStrForValue(List<String> books) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < books.size(); i++) {
            String str = books.get(i);
            if (i == 3) sb.append(str).append(", ");  // поле numeric вставляется по другому
            else {
                sb.append("'");
                sb.append(str).append("'");
                sb.append(", ");
            }
        }

        String s = sb.toString();
        int index = s.lastIndexOf(",");
        return s.substring(0, index); // обрезаем в конце запятую
    }

    // ADDITIONAL ..............................................................
    public ResultSet ajaxAddDataInTable(String tableName, List<String> arguments) {
        List<String> books = getAllColumnsNamesFromTable("books");
        String booksStr = listToStringForKey(books);
        String argumentsStr = listToStrForValue(arguments);

        ResultSet res = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO books (" + booksStr + ") VALUES(" + argumentsStr + ")")) {
            res = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int addDataInTable(String tableName, List<String> arguments) {
        int res = 0;
        try (Statement statement = connection.createStatement()) {   // SKIP _ пока не придумал как улучшить
            List<String> books = getAllColumnsNamesFromTable("books");

            String booksStr = listToStringForKey(books);
            String argumentsStr = listToStrForValue(arguments);

            res = statement.executeUpdate("INSERT INTO books (" + booksStr + ") VALUES(" + argumentsStr + ")");
// statement.executeUpdate("INSERT INTO books ("+"title, author, quantity"+") VALUES("+"'Tor', 'SHtorm', 34" + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int addColumnsByLabel(String label) {
        int res = 0;
        try (   //PreparedStatement statement = connection.prepareStatement("ALTER TABLE books ADD COLUMN ? varchar(100)")
                Statement statement = connection.createStatement()) {
            res = statement.executeUpdate("ALTER TABLE books ADD COLUMN " + label + " varchar(100)");
//            statement.setString(1, label);   //  Индекс колонки вне диапазона: 1. Допустимые значения: 1..0
//            res = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteColumnsByLabel(String label) {
        int res = 0;   // PreparedStatement не работает для DROP
        try (Statement statement = connection.createStatement()) {
            res = statement.executeUpdate("ALTER TABLE books DROP COLUMN " + label);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteOneRowById(int idCriteria) {
        int affectedRows = 0;
        try (// Statement statement = connection.createStatement() // MOT SECURE = SQL injection
             PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id=?")) {
            statement.setInt(1, idCriteria); // 1 - column индекс знака вопроса в записи, _ value ..
            affectedRows = statement.executeUpdate(); // executeUpdate - вернет, что удалось добавить или
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    // GETTERS ..............................................................
    // GOOD & SECURE with PreparedStatement .. & ..
    // PreparedStatement является предкомпилированным, то он может быть эффективно использован множество раз.
    // WRONG - без использования (with OUT) PreparedStatement - NOT SECURE for  SQL injection attack
    // https://www.postgresqltutorial.com/postgresql-jdbc/ - examples
    // https://javastudy.ru/interview/javaee-sql-jdbc-interview/ - interview

    // Функция PostgreSQL для последнего вставленного идентификатора
    // https://overcoder.net/q/9591/%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D1%8F-postgresql-%D0%B4%D0%BB%D1%8F-%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BD%D0%B5%D0%B3%D0%BE-%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B8%D0%B4%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%82%D0%BE%D1%80%D0%B0
    // java-как получить max ID в SQL
    // https://coderoad.ru/50251747/java-%D0%BA%D0%B0%D0%BA-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B8%D1%82%D1%8C-max-ID-%D0%B2-SQL
    public String getMaxIdFrom(String dataBaseName) {
        String res = null;
//        String sql1 = "SELECT MAX(id) FROM " + dataBaseName + " LIMI 1";
        String sql1 = "SELECT MAX(id) AS max_id FROM " + dataBaseName;
        try (PreparedStatement statement = connection.prepareStatement(
                sql1)) {
//            statement.setString(1, dataBaseName); // PSQLException: ОШИБКА: ошибка синтаксиса (примерное положение: "$1")
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                res = String.valueOf(resultSet.getInt("max_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String> getDataByOneColumn(String columnName) {
        List<String> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()
//                PreparedStatement statement = connection.prepareStatement("SELECT ? FROM books");   // Колонки id не найдено в этом ResultSet’’е.
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT " + columnName + " FROM books");
            while (resultSet.next()) list.add(resultSet.getString(columnName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getColumnByLabel(String label) {
        List<String> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT ? FROM books")) {
            statement.setString(1, label);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            addToListAllColumns(label, list, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAllColumnsNamesFromTable(String tableName) {
        List<String> list = new ArrayList<>();
        try (   // ошибка синтаксиса (примерное положение: "$1")    //  Позиция: 15
//                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ?")
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            addToListAllElements(list, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ResultSet getResultSetByTable(String tableName) {
        ResultSet resultSet = null;
        try {
            // ошибка синтаксиса (примерное положение: "$1") Позиция: 15
//                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ?")
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public List<String> getTablesFromDB(String dataBaseName) {
        List<String> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String[] query = {
                    // ошибка синтаксиса (примерное положение: "FROM") //  Позиция: 13
                    "SHOW TABLES FROM " + dataBaseName
                    , "SELECT * FROM " + dataBaseName
                    // WRONG
                    , "SELECT * FROM pg_catalog.pg_tables " +
                    "WHERE schemaname != 'pg_catalog' AND " +
                    "schemaname != 'information_schema'"
                    // WRONG
                    , "select table_schema, table_name " +
                    "from information_schema.tables " +
                    "where table_schema not in ('information_schema', 'pg_catalog') " +
                    "and table_type = 'BASE TABLE' order by table_schema, table_name"

                    // отношение "public.pg_tables" не существует //  Позиция: 15
                    , "SELECT * FROM public.pg_tables"
                    // отношение "pg_default.mydb" не существует //  Позиция: 15
                    , "SELECT * FROM pg_default.mydb"
                    // ссылки между базами не реализованы: "pg_default.mydb.books" //  Позиция: 15
                    , "SELECT * FROM pg_default.mydb.books"
            };
            ResultSet resultSet = statement.executeQuery(query[6]);

            addToListAllElements(list, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getTables() {
        List<String> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String[] query = {
                    "SHOW TABLES"   // нераспознанный параметр конфигурации: "tables"

                    // ОШИБКА: ошибка синтаксиса (примерное положение: "!=")
                    , "SELECT * FROM pg_catalog.pg_tables" +
                    "WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema'"

                    , "SELECT * FROM pg_catalog.pg_tables"   // WRONG
                    , "select * from information_schema.tables where table_schema='public'"    // WRONG
                    // WRONG
                    , "SELECT * FROM information_schema.tables WHERE table_type = 'BASE TABLE'" +
                    " AND table_schema = 'public' ORDER BY table_type, table_name"
            };
            ResultSet resultSet = statement.executeQuery(query[4]);

            addToListAllElements(list, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String item : list) System.out.println(item);
        return list;
    }

    public List<String> getDataBases() {
        List<String> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String[] query = {
                    // for Console pgsql & // ОШИБКА: ошибка синтаксиса (примерное положение: "\")
//                    "\l", "\dt",
                    // Good but Wrong
                    "SELECT * FROM pg_catalog.pg_tables"
                    , "SELECT * FROM pg_catalog.pg_tables" // Nothing
                    , "select * from information_schema.tables where table_schema='public'"

                    // Wrong
                    , "SELECT table_schema || '.' || table_name" +
                    " FROM" + " information_schema.tables WHERE table_type = 'BASE TABLE'" +
                    "AND table_schema NOT IN ('pg_catalog', 'information_schema')"
                    // Wrong
                    , "select * from pg_catalog.pg_tables " +
                    "where schemaname != 'information_schema'  and schemaname != 'pg_catalog';"
            };
            ResultSet resultSet = statement.executeQuery(query[0]);

            addToListAllElements(list, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String item : list) System.out.println(item);
        return list;
    }
}

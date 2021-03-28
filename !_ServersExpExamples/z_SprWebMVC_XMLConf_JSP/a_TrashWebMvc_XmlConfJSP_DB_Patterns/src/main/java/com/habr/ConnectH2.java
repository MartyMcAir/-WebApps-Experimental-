package com.habr;

import java.sql.*;

public class ConnectH2 {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver").newInstance();
//            Connection conn = DriverManager.getConnection("jdbc:h2:test", "sa", "");

//            String url = "\"jdbc:h2:~/test\"";
            String url = "jdbc:h2:mem:";
//            String url = "jdbc:h2:mem;" + "INIT=RUNSCRIPT FROM '~resources/create.sql'\\;";

            // http://www.h2database.com/html/features.html#execute_sql_on_connection
//            EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
//                    .setType(EmbeddedDatabaseType.H2)
//                    .setName("testDb;DB_CLOSE_ON_EXIT=FALSE;MODE=Oracle;INIT=create " +
//                            "schema if not exists " +
//                            "schema_a\\;create schema if not exists schema_b;" +
//                            "DB_CLOSE_DELAY=-1;")
//                    .addScript("sql/provPlan/createTable.sql")
//                    .addScript("sql/provPlan/insertData.sql")
//                    .addScript("sql/provPlan/insertSpecRel.sql")
//                    .build();

            Connection conn = DriverManager.getConnection(url, "sa", "");

            Statement st = null;
            st = conn.createStatement();
//            st.execute("CREATE TABLE IF NOT EXISTS TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
//            st.execute("CREATE TABLE TEST ( \n" +
//                    "   id INT NOT NULL, \n" +
//                    "   name VARCHAR(50) NOT NULL, \n" +
//                    "   default VARCHAR(20) NOT NULL, \n" +
//                    "   submission_date DATE \n" +
//                    ");");

//            st.execute("CREATE TABLE TEST ( \n" +
//                    "   ID INT PRIMARY KEY, \n" +
//                    "   name VARCHAR(50) NOT NULL \n" +
//                    ");");

//            http://www.mastertheboss.com/jboss-server/jboss-datasource/h2-database-cheatsheet
//             Значение NULL не разрешено для поля "ID"
//            st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
            st.execute("create table TEST(id integer, name varchar(10))");

            st.execute("INSERT INTO TEST VALUES(default,'HELLO')");
            st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
            String name1 = "Jack";
            String q = "insert into TEST(name) values(?)";
            PreparedStatement st1 = null;

            st1 = conn.prepareStatement(q);
            st1.setString(1, name1);
            st1.execute();

            ResultSet result;
            result = st.executeQuery("SELECT * FROM TEST");
            while (result.next()) {
                String name = result.getString("NAME");
                System.out.println(result.getString("ID") + " " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
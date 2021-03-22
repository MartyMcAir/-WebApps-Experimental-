package com.habr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @see http://stackoverflow.com/questions/5225700
 */

public class ConnectH2_3 {

    public static void main(String[] args) throws Exception {
//        url=jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'classpath:init.sql';DB_CLOSE_DELAY=-1;

        Connection conn = DriverManager.getConnection("jdbc:h2:mem:", "sa", "");
        Statement st = conn.createStatement();
        st.execute("create table customer(id integer, name varchar(10))");
        st.execute("insert into customer values (1, 'Thomas')");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select name from customer");
        while (rset.next()) {
            String name = rset.getString(1);
            System.out.println(name);
        }
    }
}
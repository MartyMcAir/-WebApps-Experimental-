package com;

import com.utils.HibernateHqlUtils;
import com.utils.HibernateSqlUtils;
import com.utils.JdbcSqlUtils;

public class ExperimentalDbMethods {
    public static void main(String[] args) {
        HibernateHqlUtils hibernateUtils = new HibernateHqlUtils();
        hibernateUtils.setup(); // create and save Student

//        hibernateHqlAggregateMethods();
//        hibernateSqlAggregateMethods();
//        jdbcSqlAggregateMethods();

    }

    // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-aggregate-functions.html
    private static void jpqlAggregateMethods() {

    }

    // https://www.baeldung.com/hibernate-aggregate-functions
    private static void hibernateHqlAggregateMethods() {
        HibernateHqlUtils hibernateHqlUtils = new HibernateHqlUtils();
//        hibernateUtils.setup(); // create and save Student

        hibernateHqlUtils.openSession();
        int min = hibernateHqlUtils.getMin("age", "Student");
        System.out.println("min age from Student tbl: " + min);

        int max = hibernateHqlUtils.getMax("age", "Student");
        System.out.println("max age from Student tbl: " + max);

        long sum = hibernateHqlUtils.getSum("age", "Student");
        System.out.println("sum age from Student tbl: " + sum);

        double avg = hibernateHqlUtils.getAvg("age", "Student");
        System.out.println("avg age from Student tbl: " + avg);

        long count = hibernateHqlUtils.getCount("age", "Student");
        System.out.println("count id from Student tbl: " + count);

        hibernateHqlUtils.closeSession();
    }

    private static void hibernateSqlAggregateMethods() {
        HibernateSqlUtils hibernateSqlUtils = new HibernateSqlUtils();

        int min = hibernateSqlUtils.getMin("age", "Student");
        System.out.println("min age from Student tbl: " + min);

        int max = hibernateSqlUtils.getMax("age", "Student");
        System.out.println("max age from Student tbl: " + max);

        // to find the sum of all ages
        long sum = hibernateSqlUtils.getSum("age", "Student");
        System.out.println("sum age from Student tbl: " + sum);

        // to find the average age
        double avg = hibernateSqlUtils.getAvg("age", "Student");
        System.out.println("avg age from Student tbl: " + avg);

        // Let's find the number of records in our Student table:
        long count = hibernateSqlUtils.getCount("age", "Student");
        System.out.println("count id from Student tbl: " + count);

        hibernateSqlUtils.closeSession();
    }

    // https://www.javatpoint.com/dbms-sql-aggregate-function
    // https://docs.oracle.com/database/121/SQLRF/functions003.htm#SQLRF20035
    // https://code.tutsplus.com/ru/articles/sql-for-beginners-part-3-database-relationships--net-8561
    private static void jdbcSqlAggregateMethods() {
        JdbcSqlUtils jdbcSqlUtils = new JdbcSqlUtils();
//        String maxId = jdbcSqlUtils.getMaxIdFrom( "Student");
//        System.out.println("max id from Student tbl: " + maxId);

//        String minErr = jdbcSqlUtils.getMinErr("age", "Student");
//        System.out.println("getMinErr age from Student tbl: " + minErr);

        final int min = jdbcSqlUtils.getMin("age", "Student");
        System.out.println("min age from Student tbl: " + min);

        int max = jdbcSqlUtils.getMax("age", "Student");
        System.out.println("max age from Student tbl: " + max);

        // to find the sum of all ages
        long sum = jdbcSqlUtils.getSum("age", "Student");
        System.out.println("sum age from Student tbl: " + sum);

//        // to find the average age
        double avg = jdbcSqlUtils.getAvg("age", "Student");
        System.out.println("avg age from Student tbl: " + avg);

//        // Let's find the number of records in our Student table:
        long count = jdbcSqlUtils.getCount("age", "Student");
        System.out.println("count id from Student tbl: " + count);
    }
}
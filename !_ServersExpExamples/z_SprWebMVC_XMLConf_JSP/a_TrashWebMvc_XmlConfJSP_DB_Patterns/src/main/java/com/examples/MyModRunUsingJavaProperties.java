package com.examples;

import com.model.EntitySame;
import com.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

// https://coderoad.ru/17335530/%D0%9C%D0%BE%D0%B6%D0%BD%D0%BE-%D0%BB%D0%B8-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B8%D1%82%D1%8C-Hibernate-%D0%B1%D0%B5%D0%B7-hibernate-cfg-xml
// https://howtodoinjava.com/hibernate/hibernate-hello-world-application/
// https://www.dineshonjava.com/hibernate/crud-operations-using-hibernate-3/
public class MyModRunUsingJavaProperties {
    static Properties prop = getH2BaseProperties();
    static Session session = getSession(prop);

    public static void main(String arg[]) {
        Person arti = new Person("Arti", 999);
        Person hulk = new Person("Hulk", 888);

        //
        createObj(arti);
        createObj(hulk);

        //
//        readFromDBinConsole(Person.class, "Person");
        readFromDBinConsole2();

        updObj(new Person(), 1);

        readFromDBinConsole2();

        //
        deleteObj(new Person(), 2);

        readFromDBinConsole2();
        shutdownSession();
    }

    private static void deleteObj(EntitySame entity, int id) {
        EntitySame load = session.load(entity.getClass(), id);
        session.delete(load);
    }

    private static void updObj(EntitySame entity, int id) {
        EntitySame item = (EntitySame) session.get(entity.getClass(), id);
        item.setName("Upd Namee is..");
    }

    private static void readFromDBinConsole2() {
        Query query = session.createQuery("from Person");
        List<Person> resultList = (List<Person>) query.getResultList();

        System.out.println("   ___ Reading from DataBase ___   ");
        for (Person item : resultList) System.out.println("name: " + item);
    }

    private static <T> void readFromDBinConsole(T t, String objStr) {
        Query query = session.createQuery("from " + objStr);
        List<T> resultList = (List<T>) query.getResultList();
        for (T item : resultList)
            System.out.println("name: " + item);
    }

    private static <T> void createObj(T t) {
        session.save(t);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    private static void shutdownSession() {
        session.getTransaction().commit();
        session.close();
    }

    @NotNull
    private static Session getSession(Properties prop) {
//        SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    @NotNull
    private static Properties getH2BaseProperties() {
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", "dbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");

        //You can use any database you want, I had it configured for Postgres
        prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");

        prop.setProperty("hibernate.connection.username", "sa");
        prop.setProperty("hibernate.connection.password", "");
        prop.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        prop.setProperty("show_sql", "true"); //If you wish to see the generated sql query
        return prop;
    }
}
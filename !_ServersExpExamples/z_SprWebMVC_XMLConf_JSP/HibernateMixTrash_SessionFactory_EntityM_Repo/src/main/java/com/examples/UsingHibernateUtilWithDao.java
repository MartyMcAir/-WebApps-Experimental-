package com.examples;

import com.dao.PersonDaoUsingHashMap;
import com.model.Person;
import org.hibernate.Session;

// 	Ø https://javarush.ru/groups/posts/2253-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-1
public class UsingHibernateUtilWithDao {

    public static void main(String[] args) {
//        usingHibernateUtil();
//        usingDaoHashMap();


    }

    private static void usingDaoHashMap() {
        PersonDaoUsingHashMap personDaoUsingHashMap = new PersonDaoUsingHashMap();
        personDaoUsingHashMap.add(new Person("Arti", 999));

        System.out.println(personDaoUsingHashMap.getById(0));
        System.out.println(personDaoUsingHashMap.allPersons().size());
    }

    private static void usingHibernateUtilSessionFactory() {
        Person arti = new Person("Arti", 999);
        Person hulk = new Person("Hulk", 888);

        Session session = HibernateUtilSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(arti);
        session.getTransaction().commit();

        session.beginTransaction();
        session.save(hulk);
        session.getTransaction().commit();

        session.close();
        ///////

    }
}
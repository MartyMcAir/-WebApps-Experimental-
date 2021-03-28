package com.dao;

import com.examples.HibernateUtilSessionFactory;
import com.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDaoUsingSessionFactoryByHibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public PersonDaoUsingSessionFactoryByHibernateUtil() {
        sessionFactory = HibernateUtilSessionFactory.getSessionFactory();
        session = HibernateUtilSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }

    /////////
    public Person findById(int id) {
        return session.get(Person.class, id);
    }

    public void save(Person user) {
        session.save(user);
    }

    public void update(Person user) {
        session.update(user);
    }

    public void delete(Person user) {
        session.delete(user);
    }

    public void listDevelopers() {
        List<Person> developers = (List<Person>) session.createQuery("FROM Person ").list();
        for (Person developer : developers)
            System.out.println(developer);
    }

    public Integer addDeveloper(Person developer) {
        Integer developerId = (Integer) session.save(developer);
        return developerId;
    }

    public void updateLastName(int developerId, String lastName) {
        Person developer = (Person) session.get(Person.class, developerId);
        developer.setLastName(lastName);
        session.update(developer);
    }

    public void removeDeveloper(int developerId) {
        // IllegalStateException: Transaction already active
//        Transaction transaction = session.beginTransaction();
        Person developer = (Person) session.get(Person.class, developerId);
        session.delete(developer);
//        transaction.commit();
    }
}
package com.dao;

import com.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonDaoUsingSessionFactoryWithSpring {

    @Autowired
    private SessionFactory sessionFactory;

    public void create(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    public void delete(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(person);
    }

    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    public Person getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Person> getList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person").list();
    }

    //////////
    public void persistIs2() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist("");
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Person> getList2() {
        Session session = this.sessionFactory.openSession();
        List<Person> personList = session.createQuery("from Person").list();
        session.close();
        return personList;
    }
}
package com.services;

import com.dao.PersonDaoUsingSessionFactoryWithSpring;
import com.model.Person;

import java.util.List;

public class PersonServiceUsingSessionFactory {
    private PersonDaoUsingSessionFactoryWithSpring personDAO = new PersonDaoUsingSessionFactoryWithSpring();

    public List<Person> allPersons() {
        return personDAO.getList();
    }

    public void add(Person Person) {
        personDAO.create(Person);
    }

    public void delete(Person Person) {
        personDAO.delete(Person);
    }

    public void update(Person Person) {
        personDAO.update(Person);
    }

    public Person getById(int id) {
        return personDAO.getById(id);
    }
}
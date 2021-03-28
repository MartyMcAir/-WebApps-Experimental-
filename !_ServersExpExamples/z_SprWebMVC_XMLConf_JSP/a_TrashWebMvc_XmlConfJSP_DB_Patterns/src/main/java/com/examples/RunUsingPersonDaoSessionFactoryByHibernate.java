package com.examples;

import com.dao.PersonDaoUsingSessionFactoryByHibernateUtil;
import com.model.Person;

public class RunUsingPersonDaoSessionFactoryByHibernate {
    public static void main(String[] args) {
        PersonDaoUsingSessionFactoryByHibernateUtil developersDao = new PersonDaoUsingSessionFactoryByHibernateUtil();

        System.out.println("Adding Developer's records to the database");
        Person developer1 = new Person("Proselyte", 999);
        Person developer2 = new Person("Proselyte", 888);
        Integer id1 = developersDao.addDeveloper(developer1);
        Integer id2 = developersDao.addDeveloper(developer2);

        System.out.println("\nList of Developers:");
        developersDao.listDevelopers();

        developersDao.removeDeveloper(id2);
        developersDao.updateLastName(id1, "isLast Name");

        System.out.println("\nList 2 of Developers:");
        developersDao.listDevelopers();

        developersDao.closeSession();
    }
}
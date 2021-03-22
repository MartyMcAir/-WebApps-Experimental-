package com.services;

import com.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

// using in xml <persistence-unit
public class PerSerUsingEntityManagerByXmlPersistenceUnit {
    private static final String PERSISTENCE_UNIT_NAME = "com.model.Person";

    public static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    public static void main(String[] args) {
        PerSerUsingEntityManagerByXmlPersistenceUnit instance = new PerSerUsingEntityManagerByXmlPersistenceUnit();
        instance.getList();

    }

    public List<Person> getList() {
        // https://ru.stackoverflow.com/questions/644376/%D0%BA%D0%B0%D0%BA-%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-persistencecontext
        TypedQuery<Person> q = em.createNamedQuery("findCustomersByName", Person.class);
        q.setParameter("n", "dmitriy");
        List<Person> resultList = q.getResultList();

//        for (Person p : resultList) System.out.println(p.toString());
        return resultList;
    }
}
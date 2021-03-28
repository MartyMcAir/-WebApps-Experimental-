package com.dao;

import com.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// https://javarush.ru/groups/posts/2252-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-2
public class PersonDaoUsingHashMap {
    //    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Person> personHashMap = new HashMap<>();

//    static {
//        Person test1 = new Person("test1", 999);
//        Person test2 = new Person("test2", 888);
//
//        personHashMap.put(test1.getAutoId(), test1);
//        personHashMap.put(test2.getAutoId(), test2);
//    }

//    public PersonDaoUsingHashMap() {
//        Person test1 = new Person("test1", 999);
//        Person test2 = new Person("test2", 888);
//
//        personHashMap.put(test1.getAutoId(), test1);
//        personHashMap.put(test2.getAutoId(), test2);
//    }

    public List<Person> allPersons() {
        return new ArrayList<>(personHashMap.values());
    }

    public void add(Person Person) {
//        Person.setId(AUTO_ID.getAndIncrement());
        personHashMap.put(Person.getAutoId(), Person);
    }

    public void delete(Person Person) {
        personHashMap.remove(Person.getAutoId());
    }

    public void edit(Person Person) {
        personHashMap.put(Person.getAutoId(), Person);
    }

    public Person getById(int id) {
        return personHashMap.get(id);
    }
}
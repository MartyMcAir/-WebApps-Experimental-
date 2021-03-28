package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-aggregate-functions.html
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long salary;

    public Employee() {
    }

    private Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public static Employee create(String name, int salary) {
        return new Employee(name, salary);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}

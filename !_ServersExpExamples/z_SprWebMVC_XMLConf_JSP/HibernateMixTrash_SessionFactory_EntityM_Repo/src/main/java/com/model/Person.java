package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable, EntitySame {

    // Exception: No identifier specified for entity: com.model.Customer
    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MONEY")
    private Double money;

    private int age;

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    public int getAutoId() {
        return AUTO_ID.get();
    }

    public Person() {
        AUTO_ID.getAndIncrement();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
package com.model;

import java.util.Objects;

public class Person {
    private String name, address;
    private int age;

    public Person() {
        this("Mr. Unknown", 0);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String sing() {
        return "tra-la-la";
    }

    String singRepeatedly() {
        return "tra-la-la";
//        (1..3).collect { sing() }.join("!")
    }

    Person getBestFriend() {
        return this;
    }

    static Person findSpock() {
        return new Person("Spock", 222);
    }

    //........................
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
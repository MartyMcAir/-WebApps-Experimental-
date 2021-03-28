package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class EmployeeForCriteriaApi {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long salary;
    private LocalDate joinDate;

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

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public static EmployeeForCriteriaApi create(String name, int salary, LocalDate joinDate) {
        EmployeeForCriteriaApi employee = new EmployeeForCriteriaApi();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setJoinDate(joinDate);
        return employee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                '}';
    }
}
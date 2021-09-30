package com.model;

import jdk.jfr.Enabled;

import java.util.Objects;

@Enabled
public class Employee {
    private long id;
    private String name;
    private String contactNumber;
    private String workingArea;

    public Employee() {
        super();
    }

    public Employee(final long id, final String name, final String contactNumber, final String workingArea) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.workingArea = workingArea;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(final String workingArea) {
        this.workingArea = workingArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(contactNumber, employee.contactNumber) &&
                Objects.equals(workingArea, employee.workingArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contactNumber, workingArea);
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", workingArea=" + workingArea + "]";
    }
}
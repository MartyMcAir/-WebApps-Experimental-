package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "customerid")})
public class Customer implements Serializable {

    private String name;
    private int customerid;
    private boolean isActive;

    public Customer() {
    }

    public Customer(String name, int customerId, boolean isActive) {
        this.name = name;
        this.customerid = customerId;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
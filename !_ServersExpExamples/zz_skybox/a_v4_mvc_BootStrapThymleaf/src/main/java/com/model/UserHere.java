package com.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserHere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    // OR
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private Set<Authorities> authorities = new HashSet<>();

    public UserHere() {
    }

    public UserHere(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserHere(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public List<String> getAllFields() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, Integer.toString(id), username, email, role);
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equalsWithOutPassword(UserHere userHere) {
        return id == userHere.id &&
                Objects.equals(username, userHere.username) &&
                Objects.equals(email, userHere.email) &&
                Objects.equals(role, userHere.role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHere userHere = (UserHere) o;
        return id == userHere.id &&
                Objects.equals(username, userHere.username) &&
                Objects.equals(password, userHere.password) &&
                Objects.equals(email, userHere.email) &&
                Objects.equals(role, userHere.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
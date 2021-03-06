package org.example.web.model;

import java.util.Objects;

public class User {
    private int id = 0;
    private final String name;
    private final String password;
    private final boolean isAdmin;

    public User(String name, String password, boolean isAdmin) {
        id++;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public static User getGuest() {
        return new User("guest", "", false);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isAdmin == user.isAdmin &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, isAdmin);
    }
}

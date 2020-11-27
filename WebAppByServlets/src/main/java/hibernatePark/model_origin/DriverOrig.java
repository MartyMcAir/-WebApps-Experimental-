package hibernatePark.model_origin;

import java.util.HashSet;
import java.util.Set;

public class DriverOrig {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private Set<?> buses = new HashSet<>();

    public DriverOrig() {
    }

    public void setBuses(Set<?> buses) {
        this.buses = buses;
    }

    public Set<?> getBuses() {
        return buses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}
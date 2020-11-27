package hibernatePark.model_origin;

import java.util.HashSet;
import java.util.Set;

public class RouteOrig {
    private Long id;
    private String name;
    private int number;
    private Set<?> buses = new HashSet<>();

    public RouteOrig() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBuses(Set<?> buses) {
        this.buses = buses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Set<?> getBuses() {
        return buses;
    }
}
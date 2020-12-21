package org.example.web.expForTable.frontBakend;

import org.example.web.expForTable.frontBakend.paging.Direction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class EmployeeComparators {

    static class Key {
        String name;
        Direction dir;

        public Key(String name, Direction dir) {
            this.name = name;
            this.dir = dir;
        }

        public String getName() {
            return name;
        }

        public Direction getDir() {
            return dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(name, key.name) &&
                    dir == key.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, dir);
        }
    }

    static Map<Key, Comparator<Employee>> map = new HashMap<>();

    static {
        map.put(new Key("name", Direction.asc), Comparator.comparing(Employee::getName));
        map.put(new Key("name", Direction.desc), Comparator.comparing(Employee::getName)
                .reversed());

        map.put(new Key("start_date", Direction.asc), Comparator.comparing(Employee::getStartDate));
        map.put(new Key("start_date", Direction.desc), Comparator.comparing(Employee::getStartDate)
                .reversed());

        map.put(new Key("position", Direction.asc), Comparator.comparing(Employee::getPosition));
        map.put(new Key("position", Direction.desc), Comparator.comparing(Employee::getPosition)
                .reversed());

        map.put(new Key("salary", Direction.asc), Comparator.comparing(Employee::getSalary));
        map.put(new Key("salary", Direction.desc), Comparator.comparing(Employee::getSalary)
                .reversed());

        map.put(new Key("office", Direction.asc), Comparator.comparing(Employee::getOffice));
        map.put(new Key("office", Direction.desc), Comparator.comparing(Employee::getOffice)
                .reversed());

        map.put(new Key("extn", Direction.asc), Comparator.comparing(Employee::getExtn));
        map.put(new Key("extn", Direction.desc), Comparator.comparing(Employee::getExtn)
                .reversed());
    }

    public static Comparator<Employee> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private EmployeeComparators() {
    }
}

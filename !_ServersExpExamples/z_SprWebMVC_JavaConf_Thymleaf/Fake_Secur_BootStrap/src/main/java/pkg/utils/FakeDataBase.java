package pkg.utils;

import pkg.model.User;

import java.util.*;

public final class FakeDataBase {
    private static final Map<Integer, User> map = new HashMap<>();

    public static Map<Integer, User> getMap() {
        return map;
    }

    public static boolean add(User user) {
        if (map.put(user.getId(), user) != null) return true;

        System.out.println("Add Err");
        return false;
    }

    public static boolean deleteById(int id) {
        if (map.remove(id) != null) return true;
        System.out.println("Delete Err");
        return false;
    }

    public static Optional<User> getById(int id) {
        return Optional.of(map.get(id));
    }

    public static boolean updateById(int id, User user) {
        if (getById(id).isPresent()) {
            return deleteById(id);
        } else return false;
    }
}
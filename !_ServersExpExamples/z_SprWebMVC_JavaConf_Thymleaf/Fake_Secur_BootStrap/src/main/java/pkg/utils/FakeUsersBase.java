package pkg.utils;



import pkg.model.User;

import java.util.ArrayList;
import java.util.List;

public final class FakeUsersBase {
    private static volatile FakeUsersBase fakeUsersBase = null;
    private static final List<User> usersList = new ArrayList<>();

    static {
        usersList.add(new User("root", "123", true));
    }

    private FakeUsersBase() {
    }

    public static FakeUsersBase getInstance() {
        if (fakeUsersBase == null) {
            synchronized (FakeUsersBase.class) {
                if (fakeUsersBase == null) {
                    FakeUsersBase.fakeUsersBase = new FakeUsersBase();
                }
            }
        }
        return fakeUsersBase;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void addUser(String name, String password, boolean isAdmin) {
        usersList.add(new User(name, password, isAdmin));
    }

    public void deleteUser(User user) {
        usersList.remove(user);
    }

    public User getUserById(int id) {
        User user = null;
        for (User userTmp : usersList) {
            if (userTmp.getId() == id) {
                user = userTmp;
                break;
            }
        }
        return user;
    }

    public User getUserByName(String name) {
        User user = null;
        for (User userTmp : usersList) {
            if (userTmp.getName().equals(name)) {
                user = userTmp;
                break;
            }
        }
        return user;
    }

    public boolean authenticateUser(String name, String password) {
        for (User user : usersList) {
            if (user.getName().equals(name) & user.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
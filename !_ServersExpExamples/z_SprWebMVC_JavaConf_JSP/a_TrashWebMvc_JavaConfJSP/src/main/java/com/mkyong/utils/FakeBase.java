package com.mkyong.utils;

import com.mkyong.model.User;

import java.util.ArrayList;
import java.util.List;

public final class FakeBase {

    public static List<User> getUsersList() {
        List<User> list = new ArrayList<>();
        list.add(new User("Anton"));
        list.add(new User("Anton2"));
        list.add(new User("Anton3"));
        return list;
    }
}

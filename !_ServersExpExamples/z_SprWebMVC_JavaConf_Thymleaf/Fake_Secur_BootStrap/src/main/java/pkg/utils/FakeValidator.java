package pkg.utils;

import pkg.model.User;

import java.util.Map;

public class FakeValidator {

    public static boolean validate(User user) {
        Map<Integer, User> map = FakeDataBase.getMap();
        return map.containsKey(user.getId());
    }


}
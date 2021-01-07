package com.services;

import com.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        // так же тут можно получать юзера из DAO / entityManager or repository
        User user = new User();
        user.setLogin(login);
        // SHA1 pass: 1234
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }

}
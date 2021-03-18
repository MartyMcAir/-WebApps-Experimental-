package com.services;

import com.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        User user = new User();
        user.setUsername(login);
        // new BCryptPasswordEncoder().encode("password")
        // 1234 from: https://bcrypt-generator.com/
        user.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
//        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }

}
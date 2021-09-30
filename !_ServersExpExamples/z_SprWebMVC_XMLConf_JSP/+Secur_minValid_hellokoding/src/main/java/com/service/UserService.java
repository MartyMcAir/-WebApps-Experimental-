package com.service;

import com.model.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public User findByUsername2(String username) {
        User s = new User();
        s.setUsername("root");

        // new BCryptPasswordEncoder().encode("password")
        // 1234 from: https://bcrypt-generator.com/
        s.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
        userRepository.save(s);

        return userRepository.findByUsername(username);
    }

    public User findByUsername(String login) {
        User user = new User();
        user.setUsername("root");
        // new BCryptPasswordEncoder().encode("password")
        // 1234 from: https://bcrypt-generator.com/
//        user.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
        user.setPassword(new BCryptPasswordEncoder().encode("1234"));
//        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }
}
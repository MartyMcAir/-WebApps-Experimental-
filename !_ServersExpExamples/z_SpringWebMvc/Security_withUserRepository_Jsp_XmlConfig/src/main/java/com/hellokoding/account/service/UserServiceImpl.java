package com.hellokoding.account.service;

import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.RoleRepository;
import com.hellokoding.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
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

    @Override
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
package org.doit.service;

import org.doit.model.User;
import org.doit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(String login) {
        User user = new User();
        user.setName(login);
        // new BCryptPasswordEncoder().encode("password")
        // 1234 from: https://bcrypt-generator.com/
        user.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
//        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }
}

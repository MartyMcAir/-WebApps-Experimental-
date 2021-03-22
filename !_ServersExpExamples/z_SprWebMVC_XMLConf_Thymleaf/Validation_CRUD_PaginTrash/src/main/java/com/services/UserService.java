/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.services;

import com.model.Password;
import com.model.User;
import com.model.Username;

import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository repository;
//    @Autowired
//    private PasswordEncoder encoder;

    public UserService() {
    }

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(Username username, Password password) {
//        Assert.notNull(username, "Username must not be null!");
//        Assert.notNull(password, "Password must not be null!");

        repository.findByUsername(username).ifPresent(user -> {
            throw new IllegalArgumentException("User with that name already exists!");
        });

//        Password encryptedPassword = Password.encrypted(encoder.encode(password));

//        return repository.save(new User(username, encryptedPassword));
        return repository.save(new User(username, password));
    }

    public Page<User> findAll(Pageable pageable) {
//        Assert.notNull(pageable, "Pageable must not be null!");
        return repository.findAll(pageable);
    }

    public Optional<User> findByUsername(Username username) {
//        Assert.notNull(username, "Username must not be null!");
        return repository.findByUsername(username);
    }
}
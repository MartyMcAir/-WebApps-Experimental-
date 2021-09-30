package com.services;

import com.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AuthenticationManager authenticationManager;
}
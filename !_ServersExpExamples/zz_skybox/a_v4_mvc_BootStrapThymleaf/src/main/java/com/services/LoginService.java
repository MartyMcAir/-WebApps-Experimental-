package com.services;

import com.model.forms.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        return loginFrom.getLogin().equals("root") && loginFrom.getPassword().equals("123");
    }
}
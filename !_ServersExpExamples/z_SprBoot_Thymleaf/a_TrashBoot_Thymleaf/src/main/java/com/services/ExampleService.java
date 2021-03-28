package com.services;

import com.model.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public boolean fakeAuthenticate(LoginForm lf) {
        return true;
    }
}
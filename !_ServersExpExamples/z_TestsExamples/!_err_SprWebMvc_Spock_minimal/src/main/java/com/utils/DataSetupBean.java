package com.utils;

import com.model.Customer;
import com.repositories.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
public class DataSetupBean implements InitializingBean {

    @Autowired
    private CustomerRepository repository;


    @Override
    public void afterPropertiesSet() {
        for (int i = 0; i < 10; i++)
            repository.save(new Customer("Name" + i + " ", "email@" + i + ".com"));

    }

}
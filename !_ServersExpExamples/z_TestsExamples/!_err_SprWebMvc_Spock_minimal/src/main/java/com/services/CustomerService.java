package com.services;

import com.model.Customer;
import com.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> listAll() {
        return (List<Customer>) repository.findAll();
    }

    public List<Customer> getAllCustomersBySQL() {
        return (List<Customer>) repository.findAll();
    }

    public Customer get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> search(String keyword) {
        return repository.search(keyword);
    }

    // ..............................................
    public List<String> getFakeList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("MyName");
        return strings;
    }
}
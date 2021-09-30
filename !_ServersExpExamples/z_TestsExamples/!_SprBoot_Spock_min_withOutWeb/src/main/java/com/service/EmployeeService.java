package com.service;

import com.model.Employee;
import com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public void save(Employee book) {
        repository.save(book);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Employee getById(long id) {
        return repository.findById(id).get();
    }

    public List<Employee> getListAll() {
        Iterable<Employee> all = repository.findAll();
        List<Employee> result = StreamSupport.stream(all.spliterator(),
                false).collect(Collectors.toList());
        return result;
    }
}
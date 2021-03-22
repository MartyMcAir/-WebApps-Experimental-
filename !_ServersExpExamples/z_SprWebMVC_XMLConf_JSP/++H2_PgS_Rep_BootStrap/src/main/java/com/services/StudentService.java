package com.services;

import com.model.Student;
import com.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository repository;

    public void save(Student student) {
        repository.save(student);
    }

    public List<Student> getListAll() {
        return (List<Student>) repository.findAll();
    }

    public List<Student> getListAllBySQL() {
        return (List<Student>) repository.getAllStudentsBySQL();
    }

    public Student getByID(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Student> search(String keyword) {
        return repository.search(keyword);
    }

    //    Sorting
    public List<Student> getListAllOrderByName() {
        return repository.findByOrderByNameAsc();
        // return repository.getAllStudentOrderByName_SQL();
    }

    public List<Student> getListAllOrderByAge() {
        return repository.findByOrderByAgeAsc();
    }

    // https://www.baeldung.com/spring-data-jpa-delete
    public void deleteAllData() {
        repository.deleteAll();
    }
}
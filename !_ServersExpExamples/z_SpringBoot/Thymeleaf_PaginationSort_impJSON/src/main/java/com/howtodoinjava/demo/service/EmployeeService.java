package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.utils.PageSetting;
import com.howtodoinjava.demo.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    PageSetting pageSetting;

    public void setPageSize(int pageNo) {
        pageSetting.setPageSize(pageNo);
    }

    public void setPageSortBy(String sortBy) {
        pageSetting.setSortBy(sortBy);
    }

    public PageWrapper<EmployeeEntity> getAndLoadPageBySetting(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, pageSetting.getPageSize(), Sort.by(pageSetting.getSortBy()));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper<>(all, pageNo);
    }

    public PageWrapper<EmployeeEntity> getAndLoadDefaultSizeAndSortPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10, Sort.by("id"));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper<>(all, pageNo);
    }

    public PageWrapper<EmployeeEntity> getAndLoadUniversalPageWrapper(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper<>(all, pageNo);
    }

    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html
    public List<EmployeeEntity> getAllEmployees(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeEntity> pagedResult = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + pagedResult.getTotalElements());
        System.out.println("Total pages is: " + (pagedResult.getTotalPages() - 1));
        /////////////////////////////////////////////////

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<EmployeeEntity>();
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());

        if (employee.isPresent()) {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public void save(EmployeeEntity entity) {
        repository.save(entity);
    }
}
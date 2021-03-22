package com.services;


import com.exception.RecordNotFoundException;
import com.model.EmployeeEntity;
import com.repositories.EmployeeRepository;
import com.utils.PageSetting;
import com.utils.PageWrapper_My;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public PageWrapper_My<EmployeeEntity> getAndLoadPageBySetting(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, pageSetting.getPageSize(), Sort.by(pageSetting.getSortBy()));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper_My<EmployeeEntity>(all, pageNo);
    }

    public PageWrapper_My<EmployeeEntity> getAndLoadDefaultSizeAndSortPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10, Sort.by("id"));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper_My<>(all, pageNo);
    }

    public PageWrapper_My<EmployeeEntity> getAndLoadUniversalPageWrapper_My(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeEntity> all = repository.findAll(paging);

        /////////////////////////////////////////////////
        System.out.println("Total elements is: " + all.getTotalElements());
        System.out.println("Total pages is: " + (all.getTotalPages() - 1));
        /////////////////////////////////////////////////

        return new PageWrapper_My<>(all, pageNo);
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

    // .. from
    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    public Page<EmployeeEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }
}
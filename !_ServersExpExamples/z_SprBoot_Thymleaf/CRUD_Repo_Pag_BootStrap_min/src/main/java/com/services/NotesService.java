package com.services;

import com.model.Notes;
import com.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotesService {
    @Autowired
    NotesRepository repository;

    public void save(Notes notes) {
        repository.save(notes);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Notes getById(int id) {
        return repository.findById(id).get();
    }

    public List<Notes> getListAll() {
        Iterable<Notes> all = repository.findAll();
        List<Notes> result = StreamSupport.stream(all.spliterator(),
                false).collect(Collectors.toList());
        return result;
    }

    public Page<Notes> findAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");
        return repository.findAll(pageable);
    }

    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html
    public List<Notes> getAllForPageable(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Notes> pagedResult = repository.findAll(paging);
        /////////////////////////////////////////////////
//        System.out.println("Total elements is: " + pagedResult.getTotalElements());
//        System.out.println("Total pages is: " + (pagedResult.getTotalPages() - 1));
        /////////////////////////////////////////////////

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<Notes>();
    }


    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    public Page<Notes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    // ........................................................................................
    public Notes createOrUpdateEmployee(Notes entity) {
        Optional<Notes> optional = repository.findById(entity.getId());

        if (optional.isPresent()) {
            Notes newEntity = optional.get();
            newEntity.setName(entity.getName());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}
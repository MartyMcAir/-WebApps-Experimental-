package com.services;

import com.exceptions.RecordNotFoundException;
import com.model.Book;
import com.repositories.BookRepository;
import com.utils.PageWrapper;
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
public class BookService {
    @Autowired
    BookRepository repository;

    public void save(Book book) {
        repository.save(book);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Book getById(int id) {
        return repository.findById(id).get();
    }

    public List<Book> getListAll() {
        Iterable<Book> all = repository.findAll();
        List<Book> result = StreamSupport.stream(all.spliterator(),
                false).collect(Collectors.toList());
        return result;
    }

    public Page<Book> findAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");
        return repository.findAll(pageable);
    }

    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html
    public List<Book> getAllForPageable(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> pagedResult = repository.findAll(paging);
        /////////////////////////////////////////////////
//        System.out.println("Total elements is: " + pagedResult.getTotalElements());
//        System.out.println("Total pages is: " + (pagedResult.getTotalPages() - 1));
        /////////////////////////////////////////////////

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<Book>();
    }

    public PageWrapper<Book> getAndLoadUniversalPageWrapper(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> all = repository.findAll(paging);

        return new PageWrapper<>(all, pageNo);
    }

    public PageWrapper<Book> getAndLoadDefaultSizeAndSortPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10, Sort.by("id"));
        Page<Book> all = repository.findAll(paging);

        return new PageWrapper<>(all, pageNo);
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    // ........................................................................................
    public Book createOrUpdateEmployee(Book entity) throws RecordNotFoundException {
        Optional<Book> employee = repository.findById(entity.getId());

        if (employee.isPresent()) {
            Book newEntity = employee.get();
            newEntity.setName(entity.getName());
            newEntity.setLibrary(entity.getLibrary());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}
package com.services;

import com.model.Book;
import com.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BookRepository repository;

    public void save(Book obj) {
        repository.save(obj);
    }

    public List<Book> getListAll() {
        return (List<Book>) repository.findAll();
    }

    public void deleteAllData() {
        repository.deleteAll();
    }

    public Book getByID(int id) {
        return repository.findById(id).get();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    public List<Book> search(String keyword) {
        return repository.search(keyword);
    }
}
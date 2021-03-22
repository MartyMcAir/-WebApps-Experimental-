package com.services;

import com.model.Foo;
import com.repositories.IFooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FooService extends AbstractService<Foo> implements IFooService {

    @Autowired
    private IFooRepository repository;

    public FooService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<Foo, Long> getRepository() {
        return repository;
    }

    // custom methods

    @Override
    public Page<Foo> findPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // overridden to be secured

    // https://www.baeldung.com/java-iterable-to-collection
    @Override
    @Transactional(readOnly = true)
    public List<Foo> findAll() {
        List<Foo> list = new ArrayList<>();
        Iterable<Foo> all = getRepository().findAll();
        all.forEach(list::add);
        return list;
    }

    @Override
    public Page<Foo> findPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}
package com.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

    // read - one

    @Override
    @Transactional(readOnly = true)
    public T findById(final long id) {
        return getRepository().findById(id).orElse(null);
    }

    // read - all
    // https://www.baeldung.com/java-iterable-to-collection
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        Iterable<T> all = getRepository().findAll();
        all.forEach(list::add);
        return list;
    }

    @Override
    public Page<T> findPaginated(final int page, final int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    // write

    @Override
    public T create(final T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T update(final T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(final T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        getRepository().deleteById(entityId);
    }

    protected abstract PagingAndSortingRepository<T, Long> getRepository();

}

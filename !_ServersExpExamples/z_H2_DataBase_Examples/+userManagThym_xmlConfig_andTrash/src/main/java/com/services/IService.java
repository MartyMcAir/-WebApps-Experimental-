package com.services;

import com.model.Singer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface IService<E> {
    Iterable<E> findAll();
//    E findById(Long id);
    Optional<Singer> findById(Long id);
    E save(E e);
    E remove(Long id);
}
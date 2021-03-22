package com.services;


import com.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface SingerService extends IService<Singer> {
    Page<Singer> findAllByNameContaining(String name, Pageable pageable);
}
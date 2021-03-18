package com.services;

import com.model.Country;
import com.model.Landscape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface ILandscapeService {
    Page<Landscape> findAll(Pageable pageable);
    Optional<Landscape> findById(Long id);
    void save(Landscape landscape);
    void remove(Long id);
    Iterable<Landscape> findAllByCountry(Country country);
    Page<Landscape> findAllByNameContaining(String name, Pageable pageable);
}

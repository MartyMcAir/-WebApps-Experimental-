package com.services;

import com.model.Country;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface ICountryService {
    Iterable<Country> findAll();
    Optional<Country> findById(Long id);
    void save(Country country);
    void remove(Long id);
}

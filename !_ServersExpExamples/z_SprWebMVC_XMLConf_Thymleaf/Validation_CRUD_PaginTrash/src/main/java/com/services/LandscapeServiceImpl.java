package com.services;


import com.model.Country;
import com.model.Landscape;
import com.repositories.LandscapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandscapeServiceImpl implements ILandscapeService {
    @Autowired
    private LandscapeRepository landscapeRepository;

    @Override
    public Page<Landscape> findAll(Pageable pageable) {
        return landscapeRepository.findAll(pageable);
    }

    @Override
    public Optional<Landscape> findById(Long id) {
        return landscapeRepository.findById(id);
    }

    @Override
    public void save(Landscape landscape) {
        landscapeRepository.save(landscape);
    }

    @Override
    public void remove(Long id) {
        landscapeRepository.deleteById(id);
    }

    @Override
    public Iterable<Landscape> findAllByCountry(Country country) {
        return landscapeRepository.findAllByCountry(country);
    }

    @Override
    public Page<Landscape> findAllByNameContaining(String name, Pageable pageable) {
        return landscapeRepository.findByNameContaining(name,pageable);
    }
}

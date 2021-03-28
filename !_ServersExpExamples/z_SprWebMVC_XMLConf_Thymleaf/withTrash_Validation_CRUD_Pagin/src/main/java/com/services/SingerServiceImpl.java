package com.services;

import com.repositories.SingerRepository;
import com.model.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerRepository singerRepository;

    Pageable pageable;

    public SingerServiceImpl() {
    }

    public SingerServiceImpl(Pageable pageable) {
        this.pageable = pageable;
    }

    public SingerServiceImpl(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
//    public Iterable<Singer> findAll_orig() {
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

//    @Override
//    public Iterable<Singer> findAll(@RequestParam("page") int pageIndex,
//                                    @RequestParam("size") int pageSize){
//        return itemCategoryService
//                .getAllItemCategoriesByPageable(PageRequest.of(pageIndex, pageSize)).getContent();
//    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer save(Singer product) {
        return singerRepository.save(product);
    }

    @Override
    public Singer remove(Long id) {
        Singer singer = singerRepository.findById(id).get();
        if (singer != null) singerRepository.deleteById(id);
        return singer;
    }

    @Override
    public Page<Singer> findAllByNameContaining(String name, Pageable pageable) {
        return singerRepository.findAllByNameContaining(name, pageable);
    }
}
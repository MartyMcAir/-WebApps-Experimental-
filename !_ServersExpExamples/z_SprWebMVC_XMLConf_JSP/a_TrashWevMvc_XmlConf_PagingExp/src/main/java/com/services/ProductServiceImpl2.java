package com.services;

import com.model.Product;
import com.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
// alot errors..
@Service
public class ProductServiceImpl2 implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    // Example with out implement
//    @Transactional(readOnly = true)
//    @Override
//    public Page<Product> findBySearchTerm(String searchTerm) {
//        Pageable pageRequest = createPageRequest();
//
//        //Obtain search results by invoking the preferred repository method.
////        Page<Product> searchResultPage = ...
//
//        return TodoMapper.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }

//    private Pageable createPageRequest1() {
//        //Create a new Pageable object here.
//        PageRequest pageRequest = new PageRequest(0, 10);
//        return pageRequest;
//    }
//
//    private Pageable createPageRequest2() {
//        return new PageRequest(1, 10, Sort.Direction.ASC, "title", "description");
//    }
//
//    private Pageable createPageRequest3() {
//        return new PageRequest(1, 10, new Sort(Sort.Direction.DESC, "description")
//                .and(new Sort(Sort.Direction.ASC, "title")));
//    }

}

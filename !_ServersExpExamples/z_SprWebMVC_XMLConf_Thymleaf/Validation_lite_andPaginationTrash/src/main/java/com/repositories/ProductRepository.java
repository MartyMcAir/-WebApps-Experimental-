package com.repositories;


import com.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
//public interface ProductRepository extends PagingAndSortingRepository<Singer, Long> {
    Page<Product> findAll(Pageable pageable);
}

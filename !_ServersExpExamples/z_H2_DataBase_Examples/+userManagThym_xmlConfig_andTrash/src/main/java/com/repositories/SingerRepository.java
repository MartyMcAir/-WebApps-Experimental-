package com.repositories;


import com.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//public interface SingerRepository extends JpaRepository<Singer,Long> {

@Repository
public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
    Page<Singer> findAllByNameContaining(String name, Pageable pageable);
}

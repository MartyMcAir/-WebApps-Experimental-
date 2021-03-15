package com.repositories;

import com.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface BookRepository extends JpaRepository<Book, Integer>{
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

}

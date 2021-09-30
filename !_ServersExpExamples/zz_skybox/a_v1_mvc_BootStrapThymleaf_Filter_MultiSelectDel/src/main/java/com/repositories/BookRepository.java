package com.repositories;

import com.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    @Query(value = "SELECT c FROM Book c WHERE c.id LIKE '%' || :keyword || '%'"
            + " OR c.author LIKE '%' || :keyword || '%'"
            + " OR c.title LIKE '%' || :keyword || '%'"
            + " OR c.size LIKE '%' || :keyword || '%'")
    public List<Book> search(@Param("keyword") String keyword);
}
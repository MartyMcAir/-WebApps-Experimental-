package com.repositories;

import com.model.Notes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface BookRepository extends JpaRepository<Book, Integer>{
public interface NotesRepository extends PagingAndSortingRepository<Notes, Integer> {

}

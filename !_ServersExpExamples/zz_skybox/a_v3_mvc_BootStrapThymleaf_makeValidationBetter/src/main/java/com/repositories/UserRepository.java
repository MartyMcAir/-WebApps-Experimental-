package com.repositories;

import com.model.Book;
import com.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query(value = "SELECT c FROM User c WHERE c.id LIKE '%' || :keyword || '%'"
            + " OR c.username LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'")
    public List<User> search(@Param("keyword") String keyword);

//    Optional<User> findByUsername(String username);
//
//    Optional<User> findById(int id);
//
//    Optional<User> findByEmail(String email);
}
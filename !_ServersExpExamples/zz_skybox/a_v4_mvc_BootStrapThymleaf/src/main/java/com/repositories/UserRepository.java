package com.repositories;

import com.model.UserHere;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserHere, Integer> {

    @Query(value = "SELECT c FROM User c WHERE c.id LIKE '%' || :keyword || '%'"
            + " OR c.username LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'")
    public List<UserHere> search(@Param("keyword") String keyword);

    Optional<UserHere> findByUsername(String username);

    Optional<UserHere> findById(int id);

    Optional<UserHere> findByEmail(String email);
}
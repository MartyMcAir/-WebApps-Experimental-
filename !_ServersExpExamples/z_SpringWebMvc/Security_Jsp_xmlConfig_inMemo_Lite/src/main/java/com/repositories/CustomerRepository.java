package com.repositories;


import com.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// FROM habr_482552 - !!!

// используется высокоуровневый подход, юзая CrudRepository by Spring Data _ генерирует CRUD реализацию на лету
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'"
            + " OR c.address LIKE '%' || :keyword || '%'")
    public List<Customer> search(@Param("keyword") String keyword);
}
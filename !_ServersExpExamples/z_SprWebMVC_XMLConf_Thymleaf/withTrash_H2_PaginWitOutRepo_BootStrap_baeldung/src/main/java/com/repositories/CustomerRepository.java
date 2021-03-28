package com.repositories;

import com.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// используется высокоуровневый подход, юзая CrudRepository by Spring Data _ генерирует CRUD реализацию на лету
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'"
            + " OR c.address LIKE '%' || :keyword || '%'")
    public List<Customer> search(@Param("keyword") String keyword);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query(value = "SELECT c FROM Customer c")
    public List<Customer> getAllCustomersBySQL();

}
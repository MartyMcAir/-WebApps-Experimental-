package com.repositories;

import com.model.Student;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// используется высокоуровневый подход, юзая CrudRepository by Spring Data _ генерирует CRUD реализацию на лету
@Repository
//public interface StudentRepository extends CrudRepository<Student, Long> {
// https://www.baeldung.com/spring-data-jpa-pagination-sorting с
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query(value = "SELECT c FROM Student c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.name LIKE '%' || :keyword || '%'"
            + " OR c.age LIKE '%' || :keyword || '%'")
    public List<Student> search(@Param("keyword") String keyword);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query(value = "SELECT c FROM Student c")
    public List<Student> getAllStudentsBySQL();

    // https://www.baeldung.com/spring-data-sorting
    // OR: List<Student> st = repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    List<Student> findByOrderByNameAsc();

    List<Student> findByOrderByAgeAsc();

    // https://www.baeldung.com/spring-data-jpa-delete - Spring Data JPA Delete and Relationships
    // custom query using @Query and @Modifying together
//    @Modifying
//    @Query("DELETE FROM Student b where b.name=:name")
//    void deleteStudentsByName(@Param("name") String name);

    // in down SQL don't work
//        @Query(value = "SELECT * FROM Student ORDER BY name ASC")
//    public List<Student> getAllStudentOrderByName();

    // build a Search/Filter REST API using Spring Data JPA and Specifications
    // https://www.baeldung.com/rest-api-search-language-spring-data-specifications
    // https://habr.com/ru/post/444240/ - various is bad way, with graphs.. and oth code trash..


    // some dynamic mapping capabilities of Hibernate with the @Formula, @Where, @Filter and @Any annotations.
    // https://www.baeldung.com/hibernate-dynamic-mapping
}
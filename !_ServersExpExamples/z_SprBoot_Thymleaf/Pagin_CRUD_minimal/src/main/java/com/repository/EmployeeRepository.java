package com.repository;

import com.model.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
//public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}

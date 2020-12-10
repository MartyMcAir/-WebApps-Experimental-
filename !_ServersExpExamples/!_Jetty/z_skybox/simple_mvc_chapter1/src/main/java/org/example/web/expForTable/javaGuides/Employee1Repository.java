package org.example.web.expForTable.javaGuides;

import org.example.web.expForTable.frontBakend.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee1Repository extends JpaRepository<Employee, Long> {

}
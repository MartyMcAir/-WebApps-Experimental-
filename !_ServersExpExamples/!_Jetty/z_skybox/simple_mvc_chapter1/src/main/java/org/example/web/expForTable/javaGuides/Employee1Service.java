package org.example.web.expForTable.javaGuides;

import org.example.web.expForTable.frontBakend.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Employee1Service {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

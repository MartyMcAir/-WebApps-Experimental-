package com.controllers

import com.config.MatrixWebConfig
import com.model.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = [MatrixWebConfig.class, EmployeeController.class])
class EmployeeController_NoMvcIntegrationTest extends Specification {
    @Autowired
    private EmployeeController employeeController;

    def setup() {
        employeeController.initEmployees();
    }

    public void whenInitEmployees_thenVerifyValuesInitiation() {
        Employee employee1 = employeeController.employeeMap.get(1L);
        Employee employee2 = employeeController.employeeMap.get(2L);
        Employee employee3 = employeeController.employeeMap.get(3L);

        expect:
        employee1.getId() == 1L
        employee2.getId() == 2L
        employee3.getId() == 3L

//        Assert.assertTrue(employee1.getId() == 1L);
//        Assert.assertTrue(employee1.getName().equals("John"));
//        Assert.assertTrue(employee1.getContactNumber().equals("223334411"));
//        Assert.assertTrue(employee1.getWorkingArea().equals("rh"));
//
//        Assert.assertTrue(employee2.getId() == 2L);
//        Assert.assertTrue(employee2.getName().equals("Peter"));
//        Assert.assertTrue(employee2.getContactNumber().equals("22001543"));
//        Assert.assertTrue(employee2.getWorkingArea().equals("informatics"));
//
//        Assert.assertTrue(employee3.getId() == 3L);
//        Assert.assertTrue(employee3.getName().equals("Mike"));
//        Assert.assertTrue(employee3.getContactNumber().equals("223334411"));
//        Assert.assertTrue(employee3.getWorkingArea().equals("admin"));
    }
}
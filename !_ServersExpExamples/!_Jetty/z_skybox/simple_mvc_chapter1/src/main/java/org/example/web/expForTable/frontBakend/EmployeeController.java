package org.example.web.expForTable.frontBakend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/data_employee")
public class EmployeeController {
    @GetMapping("/employee_page")
    public String main() {
        return "dataTables/frontBack";
    }
}
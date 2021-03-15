package org.example.web.expForTable.frontBakend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.example.web.controllers.LoginController;
import org.example.web.expForTable.frontBakend.paging.Column;
import org.example.web.expForTable.frontBakend.paging.Order;
import org.example.web.expForTable.frontBakend.paging.Page;
import org.example.web.expForTable.frontBakend.paging.PagingRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {
    private final Logger logger = Logger.getLogger(LoginController.class);

    private static final Comparator<Employee> EMPTY_COMPARATOR = (e1, e2) -> 0;

    public Page<Employee> getEmployees(PagingRequest pagingRequest) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //  тут делается запрос к JSON файлу
            List<Employee> employees1 = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("employees.json"),
                    new TypeReference<List<Employee>>() {
                    });

            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee(1, "name1", 10.0));
            employees.add(new Employee(2, "name2", 20.0));
            employees.add(new Employee(3, "name3", 30.0));

            return getPage(employees, pagingRequest);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return new Page<>();
    }

    private Page<Employee> getPage(List<Employee> employees, PagingRequest pagingRequest) {
        List<Employee> filtered = employees.stream()
                .sorted(sortEmployees(pagingRequest))
                .filter(filterEmployees(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = employees.stream()
                .filter(filterEmployees(pagingRequest))
                .count();

        Page<Employee> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Employee> filterEmployees(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return employee -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return employee -> employee.getName()
                .toLowerCase()
                .contains(value)
                || employee.getPosition()
                .toLowerCase()
                .contains(value)
                || employee.getOffice()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<Employee> sortEmployees(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Employee> comparator = EmployeeComparators.getComparator(column.getData(), order.getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }
}

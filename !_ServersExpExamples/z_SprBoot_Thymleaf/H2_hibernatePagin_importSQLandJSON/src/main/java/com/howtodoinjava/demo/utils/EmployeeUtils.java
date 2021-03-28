package com.howtodoinjava.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeUtils {
    private EmployeeService service;

    public EmployeeUtils(EmployeeService service) {
        this.service = service;
    }

    public List<EmployeeEntity> getDataFromJson() {
        List<EmployeeEntity> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("data.json"),
                    new TypeReference<List<EmployeeEntity>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}

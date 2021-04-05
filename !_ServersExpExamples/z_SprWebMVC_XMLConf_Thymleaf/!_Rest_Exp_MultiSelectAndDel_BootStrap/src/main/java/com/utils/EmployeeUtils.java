package com.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.EmployeeEntity;
import com.model.Product;
import com.services.EmployeeService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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


    public static void main(String[] args) throws IOException {
        // https://mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/
        ObjectMapper mapper = new ObjectMapper();
        Product obj = new Product("TestName", 3.14);

        //Object to JSON in file
        mapper.writeValue(new File("myJson.json"), obj);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);
    }
}

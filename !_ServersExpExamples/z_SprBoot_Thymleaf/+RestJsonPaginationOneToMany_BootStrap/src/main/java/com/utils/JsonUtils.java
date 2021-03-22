package com.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonUtils<T> {
    // src/main/resources/data.json
    public List<T> getDataFromJson(String jsonFile) {
        List<T> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream(jsonFile),
                    new TypeReference<List<T>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}

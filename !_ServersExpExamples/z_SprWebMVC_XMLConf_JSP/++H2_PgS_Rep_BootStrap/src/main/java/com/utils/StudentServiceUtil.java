package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Student;
import com.services.StudentService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentServiceUtil {
    private StudentService service;

    public StudentServiceUtil(StudentService service) {
        this.service = service;
    }

    public List<Student> getFilteredList(String keyword, String regex) {
        List<Student> list;

        if (regex != null) {
            Set<Student> set = new HashSet<>();
            List<Student> listAll = service.getListAll();

            for (Student obj : listAll) {
                for (String field : obj.getAllFields()) {
//                    if (field.matches(keyword)) set.add(obj); // wrong result

                    Pattern pattern = Pattern.compile(keyword);
                    Matcher matcher = pattern.matcher(field);
                    while (matcher.find()) {
                        set.add(obj);
                    }
                }
            }
            list = new ArrayList<>(set);
        } else {
            list = service.search(keyword);
        }
        return list;
    }

    public List<Student> getDataFromJson() {
        List<Student> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("students.json"),
                    new TypeReference<List<Student>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}

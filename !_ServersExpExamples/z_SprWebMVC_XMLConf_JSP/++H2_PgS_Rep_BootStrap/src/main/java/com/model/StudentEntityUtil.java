package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentEntityUtil {
    public static List<Student> getStudentsList() {
        List<Student> students = new ArrayList<>();
        Student jonas = new Student("Jonas", 22, 12);
        Student sally = new Student("Sally", 20, 34);
        Student simon = new Student("Simon", 25, 45);
        Student raven = new Student("Raven", 21, 43);
        Student sam = new Student("Sam", 23, 33);
        Collections.addAll(students, jonas, sally, simon, raven, sam);
        return students;
    }
}

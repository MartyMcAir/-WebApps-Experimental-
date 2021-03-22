package com.controllers;

import com.model.Student;
import com.utils.StudentUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Handles requests for the student model.
 * 
 */
@Controller
public class StudentController {

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            // get mock objects
            List<Student> students = StudentUtils.buildStudents();
            // add current student
            students.add(student);
            model.addAttribute("students", students);
        }
        return ((errors.hasErrors()) ? "oth/student/addStudent" : "oth/student/listStudents");
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "oth/student/addStudent";
    }

    @RequestMapping(value = "/listStudents", method = RequestMethod.GET)
    public String listStudent(Model model) {

        model.addAttribute("students", StudentUtils.buildStudents());

        return "oth/student/listStudents";
    }

}
package com.controllers;

import com.model.Student;
import com.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = {"/studentRepository"})
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ModelAndView home() {
        // studentService.listAll() - USING by default
        List<Student> list = service.getListAll();
        // studentService.getAllStudentsBySQL() - USING by your SQL
//        List<Student> list = service.getAllStudentsBySQL();

        ModelAndView mav = new ModelAndView("student/index_page");
        mav.addObject("list", list);
        return mav;
    }

    @GetMapping("/new")
    public String newStudentForm(Map<String, Object> model) {
        Student obj = new Student();
        model.put("obj", obj);
        return "student/new_page";
    }

    @PostMapping(value = "/save")
    public String saveStudent(@ModelAttribute("obj") Student obj) {
        service.save(obj);
        return "redirect:/studentRepository";
    }

    @GetMapping("/edit")
    public ModelAndView editStudentForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("student/edit_page");
        Student obj = service.getByID(id);
        mav.addObject("obj", obj);

        return mav;
    }

    @GetMapping("/delete")
    public String deleteStudentForm(@RequestParam long id) {
        service.delete(id);
        return "redirect:/studentRepository";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Student> list = service.search(keyword);
        ModelAndView mav = new ModelAndView("student/search_page");
        mav.addObject("list", list);

        return mav;
    }
}

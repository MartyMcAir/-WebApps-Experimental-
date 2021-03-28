package com.controllers;

import com.model.Student;
import com.services.StudentService;
import com.utils.StudentServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(path = {"/studentBootStrapRepository"})
public class StudentBootStrapController {

    @Autowired
    private StudentService service;
    @Autowired
    private StudentServiceUtil utils;

    @GetMapping
    public ModelAndView home() {
        // studentService.listAll() - USING by default
        List<Student> list = service.getListAll();
        // studentService.getAllStudentsBySQL() - USING by your SQL

        ModelAndView mav = new ModelAndView("student_bootstrap/index_page");
        mav.addObject("list", list);
        return mav;
    }

    @GetMapping("/new")
    public String newStudentForm(Map<String, Object> model) {
        Student obj = new Student();
        model.put("obj", obj);
        return "student_bootstrap/new_page";
    }

    @PostMapping(value = "/save")
    public String saveStudent(@ModelAttribute("obj") Student obj) {
        service.save(obj);
        return "redirect:/studentBootStrapRepository";
    }

    @GetMapping("/edit")
    public ModelAndView editStudentForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("student_bootstrap/edit_page");
        Student obj = service.getByID(id);
        mav.addObject("obj", obj);

        return mav;
    }

    @GetMapping("/delete")
    public String deleteObj(@RequestParam long id) {
        service.delete(id);
        return "redirect:/studentBootStrapRepository";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("keyword") String keyword,
                               @RequestParam(required = false, name = "regex") String regex) {
        ModelAndView mav = new ModelAndView("student_bootstrap/index_page");
        List<Student> list = utils.getFilteredList(keyword, regex);

        mav.addObject("list", list);
        return mav;
    }

    // Load Test Data in DB
    // https://forbes.kz/leader/50_bogateyshih_lyudey_mira/ - names get from here
    @GetMapping("/importTestData")
    public String importTestDataFromJson() {
        List<Student> list = utils.getDataFromJson();
        list.add(new Student("Maik Wazovskiy", 1));

        for (Student obj : list) service.save(obj);

        return "redirect:/studentBootStrapRepository";
    }

    @GetMapping(path = {"/deleteAll"})
    public String deleteAll() {
        service.deleteAllData();
        return "redirect:/studentBootStrapRepository";
    }

    //    sorting methods
    @GetMapping(path = {"/orderByName"})
    public ModelAndView orderByName() {
        List<Student> list = service.getListAllOrderByName();

        ModelAndView mav = new ModelAndView("student_bootstrap/index_page");
        mav.addObject("list", list);
        return mav;
    }

    @GetMapping(path = {"/orderByAge"})
    public ModelAndView orderByAge() {
        List<Student> list = service.getListAllOrderByAge();

        ModelAndView mav = new ModelAndView("student_bootstrap/index_page");
        mav.addObject("list", list);
        return mav;
    }
}
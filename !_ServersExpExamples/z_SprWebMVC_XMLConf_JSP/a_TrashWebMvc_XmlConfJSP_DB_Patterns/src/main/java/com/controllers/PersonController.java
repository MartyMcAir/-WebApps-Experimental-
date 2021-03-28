package com.controllers;

import com.model.Person;
import com.services.PerSerUsingEntityManagerByAnnotatPersistenceContext;
import com.services.PerSerUsingEntityManagerByXmlPersistenceUnit;
import com.services.PersonServiceUsingSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = {"/person"})
public class PersonController {

    @GetMapping(path = {"/list"})
    public String list(Model model) {
        PersonServiceUsingSessionFactory usingSessionFactory = new PersonServiceUsingSessionFactory();
        List<Person> list = usingSessionFactory.allPersons();
        System.out.println(" ___ using Session Factory ___ ");
        list.forEach(System.out::println);

        PerSerUsingEntityManagerByXmlPersistenceUnit entityManagerByXmlPersistenceUnit = new PerSerUsingEntityManagerByXmlPersistenceUnit();
        List<Person> listByXml = entityManagerByXmlPersistenceUnit.getList();
        System.out.println(" ___ using Entity Manager By Xml Persistence Unit ___ ");
        listByXml.forEach(System.out::println);

        PerSerUsingEntityManagerByAnnotatPersistenceContext annotatePersistence = new PerSerUsingEntityManagerByAnnotatPersistenceContext();
        List<Person> allUsers = annotatePersistence.getAllUsers();
        System.out.println(" ___ using annotate Persistence ___ ");
        allUsers.forEach(System.out::println);

        ////////////////
        ArrayList<Person> peoples = new ArrayList<>();
        peoples.add(new Person("Arti", 999));
        peoples.add(new Person("Hulk", 888));
        model.addAttribute("list", peoples);
        return "person/list_page";
    }

    @GetMapping(path = {"/create"})
    public String create() {
        return "person/create_page";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String delete(@PathVariable("id") int id) {
        return "person/delete_page";
    }

    @GetMapping(path = {"/update/{id}"})
    public ModelAndView update(@PathVariable("id") int id) {
//        Person person = filmService.getById(id);
        Person person = new Person("Antik", 13_000);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("person/update_page");
        modelAndView.addObject("person", person);
        return modelAndView;
    }

}
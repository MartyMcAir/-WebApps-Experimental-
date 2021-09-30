package com.controller;

import com.model.Notes;
import com.services.NotesService;
import com.utils.DataUtils;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crudNotes")
public class CRUD_NotesController {
    private final String mainPageForCurrentController = "redirect:/crudNotes/list";
    @Autowired
    NotesService service;

    // create
    @GetMapping("/create")
    public String showCreateForm_Page(Model model) {
        model.addAttribute("obj", new Notes());
        return "crudNotes/new";
    }

    @PostMapping("/save_request")
    public String submitDataFromCreateOrUpdForm_Request(@ModelAttribute("obj") Notes notes) {
        service.save(notes);
        return mainPageForCurrentController;
    }

    // delete
//    @DeleteMapping("/delete_request/{id}")
    @GetMapping("/delete_request/{id}")
    public String delete_Request(@PathVariable(value = "id") int id) {
        service.deleteById(id);
        return mainPageForCurrentController;
    }

    @GetMapping("/deleteByParam")
    public String delete_ReqParam(@RequestParam int id) {
        service.deleteById(id);
        return mainPageForCurrentController;
    }

    // update / edit / save
    @GetMapping("update/{id}")
    public String showUpdateForm_Page(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("obj", service.getById(id));
        return "crudNotes/update";
    }

    // in submitDataFromCreateOrUpdForm_Request(..)
    @PostMapping("upd_request")
    public String submitDataFromUpdateForm_Request(@ModelAttribute("obj") Notes notes) {
        service.save(notes);
        return mainPageForCurrentController;
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping(path = {"/list", "/index",})
    public String listVersion_mayBeBest_v4(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Notes> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Notes> list = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("list", list);
        return "crudNotes/listPaginationWithSort_v4";
    }

    // .................................................................................................................
    @GetMapping("/importTestData")
    public String initNotess() {
        List<String> list = DataUtils.getListStrings();
        for (String item : list) {
            service.save(new Notes(item));
        }
        return mainPageForCurrentController;
    }
}

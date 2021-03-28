package com.controller;

import com.model.Book;
import com.model.Library;
import com.repositories.BookRepository;
import com.repositories.LibraryRepository;
import com.services.BookService;
import com.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api_libraries")
public class ApiLibraryController {
    private final LibraryRepository libraryRepository;
    // if try use pagination .. Paging query needs to have a Pageable parameter!
//    private final BookRepository bookRepository;
    private final BookService service;

    @Autowired
    public ApiLibraryController(LibraryRepository libraryRepository, BookService service) {
        this.libraryRepository = libraryRepository;
//        this.bookRepository = bookRepository;
        this.service = service;
    }

    @GetMapping("/importTestData")
    public ModelAndView initBooks() {
        // сначала создается библиотека и потом ярлык на неё добавлякется к каждой книге
        // книги без библиотеки не может быть
        Library library = new Library("My Library Name");
        libraryRepository.save(library);

        List<String> list = DataUtils.getListStrings();
        for (String item : list) {
            service.save(new Book(item, library));
        }

        return new ModelAndView("redirect:/api_libraries/listVersion_v4");
    }

    // https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping("/listVersion_v4")
    public ModelAndView listVersion_mayBeBest_v4(ModelAndView modelAndView) {
        return findPaginated(1, "name", "asc", modelAndView);
    }

    @GetMapping("/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir, ModelAndView modelAndView) {
        // if use Repository: Paging query needs to have a Pageable parameter!
        Page<Book> page = service.findPaginated(pageNo, 5, sortField, sortDir);

//        ModelAndView modelAndView = new ModelAndView("oneToMay/listVersion_v4");
        modelAndView.setViewName("oneToMay/listVersion_v4");
        modelAndView.addObject("currentPage", pageNo);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalItems", page.getTotalElements());
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortDir", sortDir);
        modelAndView.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("list", page.getContent());

        return modelAndView;
    }

    //..................................................................................................................
    @PostMapping
    public ResponseEntity<Library> create(@Valid @RequestBody Library library) {
        Library savedLibrary = libraryRepository.save(library);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLibrary.getId()).toUri();

        return ResponseEntity.created(location).body(savedLibrary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> update(@PathVariable Integer id, @Valid @RequestBody Library library) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        library.setId(optionalLibrary.get().getId());
        libraryRepository.save(library);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> delete(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libraryRepository.delete(optionalLibrary.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getById(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalLibrary.get());
    }

    @GetMapping
    public ResponseEntity<Page<Library>> getAll(Pageable pageable) {
        return ResponseEntity.ok(libraryRepository.findAll(pageable));
    }
}

package com.controller;

import com.model.Book;
import com.model.Library;
import com.repositories.BookRepository;
import com.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api_books")
public class ApiBookController {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public ApiBookController(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bookRepository.findAll(pageable));
    }

    @GetMapping(path = {"/init"})
    public String initBooks() {
        // сначала создается библиотека и потом ярлык на неё добавлякется к каждой книге
        // книги без библиотеки не может быть
        Library library = new Library("My Library Name");
        libraryRepository.save(library);

        bookRepository.save(new Book("Iron Is Begun", library));
        bookRepository.save(new Book("Iron Is Trust", library));
        bookRepository.save(new Book("Iron Is Hope", library));
        bookRepository.save(new Book("Iron Is Armor", library));
        bookRepository.save(new Book("Iron Is Heavy", library));
        bookRepository.save(new Book("Iron Is Treasure", library));
        bookRepository.save(new Book("Iron Is Everything", library));
        return "";
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid Book book) {
        Optional<Library> optionalLibrary = libraryRepository.findById(book.getLibrary().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        book.setLibrary(optionalLibrary.get());

        Book savedBook = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBook.getId()).toUri();

        return ResponseEntity.created(location).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody @Valid Book book, @PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(book.getLibrary().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        book.setLibrary(optionalLibrary.get());
        book.setId(optionalBook.get().getId());
        bookRepository.save(book);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        bookRepository.delete(optionalBook.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalBook.get());
    }

    @GetMapping("/library/{libraryId}")
    public ResponseEntity<Library> getByLibraryId(@PathVariable Integer libraryId) {
        return ResponseEntity.ok(libraryRepository.findById(libraryId).get());
    }
}
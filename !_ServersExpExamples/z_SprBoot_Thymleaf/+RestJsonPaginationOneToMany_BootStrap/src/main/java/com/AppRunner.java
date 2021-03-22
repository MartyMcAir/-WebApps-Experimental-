package com;

import com.model.Book;
import com.model.Library;
import com.repositories.BookRepository;
import com.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AppRunner {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/myContextPath");
        SpringApplication.run(AppRunner.class, args);
    }

    // https://reflectoring.io/spring-boot-execute-on-startup/
    @PostConstruct
    private void init() {
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
    }
}
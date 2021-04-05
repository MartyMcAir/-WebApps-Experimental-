package com.utils;

import com.model.Book;
import com.repositories.BookRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSetupBean implements InitializingBean {

    @Autowired
    private BookRepository repository;
    @Autowired
    private BookServiceUtils utils;

    @Override
    public void afterPropertiesSet() {
        List<Book> dataFromJson = utils.getDataFromJson("data_ruAuthor.json");
        dataFromJson.forEach(v -> repository.save(v));
    }
}
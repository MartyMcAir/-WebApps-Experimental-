package com.utils;

import com.model.Book;
import com.model.UserHere;
import com.repositories.BookRepository;
import com.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSetupBean implements InitializingBean {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookServiceUtils bookServiceUtils;

    @Autowired
    private UserServiceUtils userServiceUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() {
        List<Book> bookList = bookServiceUtils.getDataFromJson("json/data_ruAuthor.json");
        bookList.forEach(v -> bookRepository.save(v));

        // data_users_PiedPiper.json data_users_ruJavaMen.json
        List<UserHere> userHereList = userServiceUtils.getDataFromJson("json/data_users_PiedPiper.json");
        userHereList.forEach(v -> userRepository.save(v));
    }
}
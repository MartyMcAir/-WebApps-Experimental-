package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Book;
import com.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BookServiceUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService service;

    // id author title size
    public List<Book> getFilteredList(String idKey, String authorKey, String titleKey, String sizeKey, String regex) {
        List<Book> listAll = service.getListAll();
        Set<Book> set = new HashSet<>();

        if (regex == null) { // if (regex.isEmpty())
            for (Book obj : listAll) {
                if (!idKey.isEmpty())
                    if (String.valueOf(obj.getId()).contains(idKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!authorKey.isEmpty())
                    if (obj.getAuthor().contains(authorKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!titleKey.isEmpty())
                    if (obj.getTitle().contains(titleKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!sizeKey.isEmpty())
                    if (String.valueOf(obj.getSize()).contains(sizeKey))
                        set.add(obj);
            }
            return new ArrayList<>(set);
        }

        // if regex True (on)

        boolean flag;
        for (Book obj : listAll) {
            flag = false;

            if (!idKey.isEmpty()) {
                Matcher matcher = Pattern.compile(idKey).matcher(String.valueOf(obj.getId()));
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!authorKey.isEmpty()) {
                Matcher matcher = Pattern.compile(authorKey).matcher(obj.getAuthor());
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!titleKey.isEmpty()) {
                Matcher matcher = Pattern.compile(titleKey).matcher(obj.getTitle());
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!sizeKey.isEmpty()) {
                Matcher matcher = Pattern.compile(sizeKey).matcher(String.valueOf(obj.getSize()));
                while (matcher.find()) set.add(obj);
            }
        }
        return new ArrayList<>(set);
    }

    private boolean isFlag(Set<Book> set, boolean flag, Book obj, Matcher matcher) {
        while (matcher.find()) {
            set.add(obj);
            flag = true;
        }
        return flag;
    }

    public List<Book> getSearchList(String keyword, String regex) {
        if (regex.isEmpty()) return service.search(keyword);

        Set<Book> set = new HashSet<>();
        List<Book> listAll = service.getListAll();

        for (Book obj : listAll) {
            for (String field : obj.getAllFields()) {
                Pattern pattern = Pattern.compile(keyword);
                Matcher matcher = pattern.matcher(field);

                while (matcher.find()) set.add(obj);
            }
        }

        return new ArrayList<>(set);
    }

    public List<Book> getDataFromJson(String fileName) {
        List<Book> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream(fileName),
                    new TypeReference<List<Book>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
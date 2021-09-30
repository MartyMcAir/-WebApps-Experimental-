package com.utils;

import com.model.Book;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TmpExpTest {
    private static BookServiceUtils utils = new BookServiceUtils();
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public static void main(String[] args) {
//        early();
//        regexExpTest();
//        checkPatternForNumbers();

        System.out.println("martymcair@gmail.com".matches(EMAIL_PATTERN));
    }

    private static void checkPatternForNumbers() {
        Pattern pattern = Pattern.compile("[0-8]");
        Matcher matcher = pattern.matcher("387");

        boolean flag = false;
        while (matcher.find()) {
            flag = true;
        }
        System.out.println(flag);
    }

    private static void regexExpTest() {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("morning");

        boolean flag = false;
        while (matcher.find()) {
            flag = true;
        }
        System.out.println(flag);
    }

    private static void early() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();

        fillBook(book1, 1, "aut1", "tit1", 1);
        fillBook(book2, 2, "aut12", "kak", 1);
        fillBook(book3, 3, "kak", "tit1", 1);
        fillBook(book4, 4, "kak", "kak", 1);

        List<Book> list = new LinkedList<>();
        Collections.addAll(list, book1, book2, book3, book4);


        // TEST
//        List<Book> filteredList = utils.getFilteredList(null, "\\d", "9", "9", "notNull", list);
        List<Book> filteredList = utils.getFilteredList(null, "\\d", "9", "9", "notNull");
        filteredList.forEach(System.out::println);
    }

    public static void fillBook(Book obj, int id, String author, String title, int size) {
        obj.setId(id);
        obj.setAuthor(author);
        obj.setTitle(title);
        obj.setSize(size);
    }
}
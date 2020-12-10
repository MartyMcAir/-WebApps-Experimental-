package org.example.web.expForTable.baeldung;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {
    public static List<MyBook> buildBooks() {
        List<MyBook> books = new ArrayList<>();
        books.add(new MyBook(1, "Java"));
        books.add(new MyBook(2, "HTML"));
        books.add(new MyBook(3, "CSS"));
        books.add(new MyBook(4, "JavaScript"));
        books.add(new MyBook(5, "Dart js"));
        books.add(new MyBook(6, "Spring Framework"));

        return books;
    }
}
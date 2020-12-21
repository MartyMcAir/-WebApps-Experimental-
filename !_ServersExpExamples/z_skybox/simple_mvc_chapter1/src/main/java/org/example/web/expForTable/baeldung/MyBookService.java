package org.example.web.expForTable.baeldung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class MyBookService {

    final private List<MyBook> books = BookUtils.buildBooks();

    public Page<MyBook> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MyBook> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<MyBook> bookPage = new PageImpl<MyBook>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }
}
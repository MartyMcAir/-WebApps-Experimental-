package com.utils;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageWrapper<T> {
    private final Page<T> pageResult;
    private final List<T> listContent;
    private int totalElements = 0;
    private int totalPages = 0;
    private int currentPage = 0;

    public PageWrapper(Page<T> pageResult, int currentPage) {
        this.pageResult = pageResult;
        this.currentPage = currentPage;

        if (pageResult.hasContent()) {
            this.listContent = pageResult.getContent();
            this.totalElements = pageResult.getNumberOfElements();
            this.totalPages = pageResult.getTotalPages() - 1;
        } else listContent = new ArrayList<T>();
    }

    public Page<T> getPageResult() {
        return pageResult;
    }

    public List<T> getListContent() {
        return listContent;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
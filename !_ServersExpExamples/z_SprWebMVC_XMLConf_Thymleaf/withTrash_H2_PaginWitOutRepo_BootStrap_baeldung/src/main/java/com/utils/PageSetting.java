package com.utils;

import org.springframework.stereotype.Component;

@Component
public class PageSetting {
    private int pageSize = 10;
    private String sortBy = "id";

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
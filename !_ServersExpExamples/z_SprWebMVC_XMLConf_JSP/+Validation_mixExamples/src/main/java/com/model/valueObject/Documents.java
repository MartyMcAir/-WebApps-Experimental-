package com.model.valueObject;

import java.util.ArrayList;
import java.util.List;

public class Documents {

    private List<String> tests = new ArrayList();

    public Documents(List<String> tests) {
        this.tests.addAll(tests);
    }

    public boolean contains(String test) {
        return this.tests.contains(test);
    }
}
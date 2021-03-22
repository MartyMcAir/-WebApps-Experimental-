package com.jpaLoadFromXml.intf;


import com.jpaLoadFromXml.supportClasses.ContactSummary;

import java.util.List;

public interface ContactSummaryService {
    public List<ContactSummary> findAllSummary();
}

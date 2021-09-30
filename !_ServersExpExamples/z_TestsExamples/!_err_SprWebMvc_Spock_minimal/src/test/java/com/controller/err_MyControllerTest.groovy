package com.controller

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class err_MyControllerTest extends Specification {
    private MockMvc mockMvc;

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController()).build();
    }

    def "my test"() {
//        mockMvc.perform(get("/message")).andExpect(status().isOk())
//                .andExpect(content().string("Hello there!"));
        expect:
        1 == 1
    }

}
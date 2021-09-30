package com.controllers;

import com.config.EmployeeConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = {EmployeeConfiguration.class, EmployeeController.class})
public class Tmp_Experimental_MockMvc {
    @Autowired
    private static WebApplicationContext webAppContext;
    @Autowired
    private static MockMvc mockMvc;

    public static void main(String[] args) throws Exception {
//        MockitoAnnotations.initMocks(new Tmp());
//        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();

        //
        MockHttpServletRequestBuilder getMock = MockMvcRequestBuilders.get("/employee");

        System.out.println(getMock);

    }
}
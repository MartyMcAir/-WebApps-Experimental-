package com.controllers;

import com.config.MatrixWebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebAppConfiguration
@ContextConfiguration(classes = {MatrixWebConfig.class, EmployeeController.class})
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
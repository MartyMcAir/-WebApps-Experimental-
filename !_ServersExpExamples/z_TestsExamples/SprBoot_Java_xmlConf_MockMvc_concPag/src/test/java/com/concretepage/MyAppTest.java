package com.concretepage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config.xml")
@WebAppConfiguration
public class MyAppTest {
    @Autowired
    protected WebApplicationContext webAppContext;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(webAppContext).build();
    }
    @Test
    public void webAppContextTest() throws Exception {
    	assertTrue(webAppContext.getServletContext() instanceof MockServletContext);
    }

    @Test
    public void getRequestTest() throws Exception {
        URI uri = UriComponentsBuilder.fromUriString("/hello")
        		.pathSegment("Mahesh").build().encode().toUri();
        mockMvc.perform(get(uri)).andExpect(status().isOk())
                .andExpect(content().string("Hello Mahesh"));
    }

    @Test
    public void postRequestTest() throws Exception {
        URI uri = UriComponentsBuilder.fromUriString("/save").build().encode().toUri();
        mockMvc.perform(
        		  post(uri)
        		  .param("name", "Ram")
                  .contentType(MediaType.APPLICATION_FORM_URLENCODED) 
                  .accept(MediaType.APPLICATION_JSON)
        		)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }	    
}
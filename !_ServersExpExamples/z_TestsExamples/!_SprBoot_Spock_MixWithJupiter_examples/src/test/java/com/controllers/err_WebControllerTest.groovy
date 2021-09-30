package com.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

// ERR, here its normal

// https://www.baeldung.com/spring-spock-testing
@AutoConfigureMockMvc
@ContextConfiguration(locations = "/rest-servlet.xml")
@WebMvcTest
class err_WebControllerTest extends Specification {
    @Autowired
    private MockMvc mvc

    def "when get is performed then the response has status 200 and content is 'Hello world!'"() {
        ResultActions perform = mvc.perform(get("/hello"))
        ResultActions expect = perform.andExpect(status().isOk)
        MockHttpServletResponse response = perform.andReturn().response

        expect: "Status is 200 and the response is 'Hello world!'"
        response.contentAsString == "Hello world!"

//        mvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .response
//                .contentAsString == "Hello world!"
    }
}
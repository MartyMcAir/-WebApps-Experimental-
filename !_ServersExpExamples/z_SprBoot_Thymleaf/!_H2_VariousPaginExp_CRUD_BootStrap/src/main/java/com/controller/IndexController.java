package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    // localhost:8080/myContextPath/
    @GetMapping(path = {"/", "/home"})
    public String statPage(Model model) {
        return "index";
    }

    @GetMapping("/startPage")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adminRoot(final HttpServletRequest request, final HttpServletResponse response) {
        final String rootUri = request.getRequestURL()
                .toString();

//        final URI fooUri = new UriTemplate("{rootUri}{resource}").expand(rootUri, "foos");
//        final String linkToFoos = LinkUtil.createLinkHeader(fooUri.toASCIIString(), "collection");
//        response.addHeader("Link", linkToFoos);
    }
}
package com.config

import com.config.CustomerConfiguration
import com.controllers.WebController
import com.vo.CollectionProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

// ERR, here its normal

// https://www.baeldung.com/spring-spock-testing
//@SpringBootTest
@SpringBootTest(classes = CustomerConfiguration.class)
class err_ContextTest extends Specification {
    @Autowired(required = false)
    private WebController webController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        webController
    }
}
package com.vo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriorityProvider.class)
class PriorityProvider_IntegrationTest extends Specification {
//    @Autowired (required = false)
    @Autowired
    private PriorityProvider priorityProvider;

    def "GetPriority"() {
        expect:
        priorityProvider.getPriority() == "high"
    }
}
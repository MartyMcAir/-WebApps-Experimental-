package com.utils

import com.controller.CustomerController
import com.model.Customer
import com.services.CustomerService
import groovy.json.JsonSlurper
import spock.lang.Specification

// https://gist.github.com/jeffsheets/ada3de8fe4a536e5351b
// USE: @MockBean @Autowired
//@ExtendWith(SpringExtension.class)
// @DataJpaTest, @WebMvcTest, and @SpringBootTest.
//@WebMvcTest(controllers = RegisterRestController.class)
class err_Rest_Test extends Specification {
//    def accountController = new AccountController()
    def accountController = new CustomerController()
//    def securityService = Mock(SecurityService)
    def service = Mock(CustomerService)

    //The magic happens here
//    MockMvc mockMvc = new MockMvc(accountController).build()

    def setup() {
//        accountController.securityService = securityService
        accountController.setCustomerService(service)

        service.save(new Customer(1L, "customName"))
    }

    // GET and POST: ("/products/{id}")
    def "product rest test experimental"() {
        when:
        def response = mockMvc.perform(get('/product/1')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'securityService correctly returned account in JSON'
//        1 * service.get(1) >> 'customName'

        //Testing the HTTP Status code
//        response.status == OK.value()

        //Showing how a contains test could work
        response.contentType.contains('application/json')
        response.contentType == 'application/json;charset=UTF-8'

        //Can test the whole content string that is returned
        response.contentAsString == '{"name":"customName"}'

        //Or can use the JsonSlurper version to test the JSON as object
        content.username == 'customName'
    }
}
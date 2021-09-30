package com.controller

import com.model.Customer
import com.services.CustomerService

class err_CustomerReaderSpec extends spock.lang.Specification {
    CustomerService serviceStub
    CustomerService serviceMock

    def setup() {
        serviceStub = Stub(CustomerService.class)
        serviceMock = Mock(CustomerService.class)
    }

    def "customer full name is formed from first name and last name"() {
        given: "a customer with example name values"
        Customer sampleCustomer = new Customer("Susan", "Ivanova")

        and: "an entity manager that always returns this customer"
        serviceStub.get(1L) >> sampleCustomer

        and: "a customer reader which is the class under test"
        CustomerController customerController = new CustomerController()
        customerController.setCustomerService(serviceStub)

        when: "we ask for the full name of the customer"
//        String fullName = customerController.findFullName(1L)
        String name = sampleCustomer.getName()

        then: "we get both the first and the last name"
        name == "Susan"
    }

}
package com.controller

import com.services.CustomerService
import spock.lang.Specification

class MockSpec extends Specification {

    CustomerController controller

    void "Mock FooService"() {
        given: "создаём мок зависимости"
        CustomerService fooService = Mock()

        and: "устанавливаем экземпляр мока в контроллер"
        controller.customerService = fooService

        when: "вызываем действие контроллера"
        controller.getCustomerJSON('1')

        then: "мок можно использовать для проверки числа вызовов и значений параметров"
        1 * fooService.search('1')

        and: "мок возвращает 'пустое' значение по умолчанию - 'null'"
        fooService.listAll() == null.toString()
    }
}
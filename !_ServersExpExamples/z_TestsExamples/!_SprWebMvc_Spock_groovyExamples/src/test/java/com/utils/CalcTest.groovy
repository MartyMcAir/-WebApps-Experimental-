package com.utils

import spock.lang.Specification
import spock.lang.Unroll

class CalcTest extends Specification {
    Calc calc = new Calc();

    def "it sum two numbers"() {
//        given: "Setup _ ДАНО"
//        when: "Stimulus _ ДЕЙСТВИЕ"
//        then: "Response _ ОТВЕТ _ проверка результата от действия"
//        expect: "Stimulus and Response _ и то и другое"
//        cleanup: "Cleanup _ refresh for another tests"
//        where: "multiples variables initialize for test's"

        expect: "Stimulus and Response"
//        calc.sum(a, b) == 4
        calc.sum(a, b) == (a + b)

        where: "variables initialize"
        a | b
        1 | 3
        3 | 6
        6 | 9
    }
}
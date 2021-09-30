package com.utils

import spock.lang.Specification

class CalcTest extends Specification {
    Calc calc = new Calc();

    def "Sum"() {
        expect:
        9 == calc.sum(6, 3)
    }

    def "Subtract"() {
        expect:
        3 == calc.subtract(6, 3)
    }
}
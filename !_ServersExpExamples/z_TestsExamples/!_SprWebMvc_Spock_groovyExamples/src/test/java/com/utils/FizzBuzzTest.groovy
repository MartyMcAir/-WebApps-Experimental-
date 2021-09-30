package com.utils

import spock.lang.Specification

//import org.junit.Before
//import org.junit.Test

class FizzBuzzTest extends Specification {

    def fizzBuzz

//    @Before
    void setup() {
        fizzBuzz = new FizzBuzz()
    }

//    @Test
    void 'should be fizz'() {
//        assert fizzBuzz.play(3) == 'Fizz'
        expect:
        fizzBuzz.play(3) == 'Fizz'
    }

//    @Test
    void 'should be buzz'() {
//        assert fizzBuzz.play(5) == 'Buzz'
        expect:
        fizzBuzz.play(5) == 'Buzz'
    }

//    @Test
    void 'should be fizz buzz'() {
//        assert fizzBuzz.play(1) == 'FizzBuzz'
        expect:
        fizzBuzz.play(1) == 'FizzBuzz'
    }

}
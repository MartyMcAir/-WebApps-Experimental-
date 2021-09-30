package com.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {
    Calc calc = new Calc();
    BigDecimal a = new BigDecimal(2), b = new BigDecimal(3);

    @Test
    void sum() {
        assertTrue(calc.sum(a, b).equals(new BigDecimal(5)));
    }

    @Test
    void subtract() {
        assertEquals(new BigDecimal(-1), calc.subtract(a, b));
    }
}
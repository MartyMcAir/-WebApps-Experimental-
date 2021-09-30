package com.utils;

import java.math.BigDecimal;

public class Calc {
    public BigDecimal sum(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    public BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }
}
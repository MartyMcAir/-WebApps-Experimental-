package com.model;

import com.error.NegativeAmountWithdrawnException;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = new BigDecimal(0);

    public Account(BigDecimal initial) {
        balance = initial;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) throws NegativeAmountWithdrawnException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmountWithdrawnException(amount);
        }
        balance = balance.subtract(amount);
    }
}
  
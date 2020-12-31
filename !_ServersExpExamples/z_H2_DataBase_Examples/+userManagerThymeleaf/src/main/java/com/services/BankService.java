package com.services;

import com.model.Bank;

import java.util.List;

public interface BankService {
    Bank addBank(Bank bank);

    void deleteById(long id);

    Bank getByName(String name);

    Bank editBank(Bank bank);

    List<Bank> getAll();
}
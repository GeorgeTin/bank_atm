package com.bank.atm.services;

import com.bank.atm.errors.NotEnoughAssetsException;
import com.bank.atm.helpers.Currency;
import com.bank.atm.entities.BankAccount;

import java.util.Map;

public interface ATMService {
    void withdraw(BankAccount bankAccount, Double amount, Currency currency) throws NotEnoughAssetsException;
    void deposit(BankAccount bankAccount, Double amount, Currency currency);
    Map<Currency, Double> interrogate(BankAccount bankAccount);
}

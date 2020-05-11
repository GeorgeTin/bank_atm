package com.bank.atm.services;

import com.bank.atm.entities.ATM;
import com.bank.atm.errors.NotEnoughAssetsException;
import com.bank.atm.helpers.Currency;
import com.bank.atm.entities.BankAccount;

import java.util.Map;
import java.util.Optional;

public interface ATMService {
    void withdraw(BankAccount bankAccount, Double amount, Currency currency) throws NotEnoughAssetsException;
    void deposit(BankAccount bankAccount, Double amount, Currency currency);
    Map<Currency, Double> interrogate(BankAccount bankAccount);
    Optional<ATM> findById(Long id);
}

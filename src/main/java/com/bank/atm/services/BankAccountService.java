package com.bank.atm.services;

import com.bank.atm.entities.Card;
import com.bank.atm.entities.BankAccount;

import java.util.Optional;

public interface BankAccountService {
    Optional<BankAccount> findBankAccountByCard(Card card);
}

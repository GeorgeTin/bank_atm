package com.bank.atm.services.impl;

import com.bank.atm.entities.Card;
import com.bank.atm.entities.BankAccount;
import com.bank.atm.repositories.BankAccountRepository;
import com.bank.atm.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Optional<BankAccount> findBankAccountByCard(Card card) {
        return bankAccountRepository.findBankAccountByCard(card);
    }
}

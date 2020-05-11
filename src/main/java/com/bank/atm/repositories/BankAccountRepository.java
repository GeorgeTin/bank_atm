package com.bank.atm.repositories;

import com.bank.atm.entities.BankAccount;
import com.bank.atm.entities.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    Optional<BankAccount> findBankAccountByCard(Card card);
}

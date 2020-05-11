package com.bank.atm.controllers;

import com.bank.atm.dtos.CardDTO;
import com.bank.atm.errors.InvalidCardException;
import com.bank.atm.errors.NotFoundBankAccountException;
import com.bank.atm.entities.BankAccount;
import com.bank.atm.entities.Card;
import com.bank.atm.helpers.Currency;
import com.bank.atm.services.ATMService;
import com.bank.atm.services.BankAccountService;
import com.bank.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/atm")
public class ATMController {

    private ATMService atmService;
    private BankAccountService bankAccountService;
    private CardService cardService;

    @Autowired
    public ATMController(ATMService atmService, BankAccountService bankAccountService, CardService cardService) {
        this.atmService = atmService;
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
    }

    @PostMapping
    public Map<Currency, Double> interrogateBankAccount(@RequestBody CardDTO cardDTO, @RequestParam Long atmId) throws InvalidCardException,
            NotFoundBankAccountException {

//      use atmId for logging

        Optional<Card> maybeCard = cardService.findByCardInfo(cardDTO);
        maybeCard.orElseThrow(InvalidCardException::new);
        Card card = maybeCard.get();

        Optional<BankAccount> maybeBankAccount = this.bankAccountService.findBankAccountByCard(card);
        maybeBankAccount.orElseThrow(NotFoundBankAccountException::new);
        BankAccount bankAccount = maybeBankAccount.get();

        return atmService.interrogate(bankAccount);
    }

}

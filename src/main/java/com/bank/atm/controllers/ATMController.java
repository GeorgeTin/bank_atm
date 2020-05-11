package com.bank.atm.controllers;

import com.bank.atm.dtos.CardDTO;
import com.bank.atm.entities.ATM;
import com.bank.atm.errors.InvalidCardException;
import com.bank.atm.errors.NotFoundATMException;
import com.bank.atm.errors.NotFoundBankAccountException;
import com.bank.atm.entities.BankAccount;
import com.bank.atm.entities.Card;
import com.bank.atm.helpers.Currency;
import com.bank.atm.services.ATMService;
import com.bank.atm.services.BankAccountService;
import com.bank.atm.services.CardService;
import com.bank.atm.services.impl.ATMServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ATMController.class);


    @Autowired
    public ATMController(ATMService atmService, BankAccountService bankAccountService, CardService cardService) {
        this.atmService = atmService;
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
    }

    /**
     * Endpoint used to interrogate the sold. It return a dictionary: Currency:Sold, for all the
     * Currencies that have sold different than zero.
     * @param cardDTO information stored on a normal card used to identify the customer
     * @param atmId id of the atm, used to log useful information.
     * @return sold per currency.
     * @throws InvalidCardException
     * @throws NotFoundBankAccountException
     * @throws NotFoundATMException
     */
    @PostMapping
    public Map<Currency, Double> interrogateBankAccount(@RequestBody CardDTO cardDTO, @RequestParam Long atmId)
            throws InvalidCardException, NotFoundBankAccountException, NotFoundATMException {

        logger.info("Interrogation ATM: " + atmId);

        Optional<ATM> atm = atmService.findById(atmId);
        atm.orElseThrow(NotFoundATMException::new);

        Optional<Card> maybeCard = cardService.findByCardInfo(cardDTO);
        maybeCard.orElseThrow(InvalidCardException::new);
        Card card = maybeCard.get();

        Optional<BankAccount> maybeBankAccount = this.bankAccountService.findBankAccountByCard(card);
        maybeBankAccount.orElseThrow(NotFoundBankAccountException::new);
        BankAccount bankAccount = maybeBankAccount.get();

        return atmService.interrogate(bankAccount);
    }

}

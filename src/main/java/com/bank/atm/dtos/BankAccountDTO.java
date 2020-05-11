package com.bank.atm.dtos;

import com.bank.atm.entities.Bank;

public class BankAccountDTO {
    private Long id;
    private String iban;
    private String accountNumber;
    private Bank bank;
    private String version;
}

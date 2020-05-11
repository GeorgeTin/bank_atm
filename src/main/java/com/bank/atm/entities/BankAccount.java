package com.bank.atm.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String iban;

    @NotNull
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = true)
    private Card card;

    public BankAccount(Long id, @NotNull String iban, @NotNull String accountNumber, Bank bank, Card card) {
        this.id = id;
        this.iban = iban;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.card = card;
    }
}

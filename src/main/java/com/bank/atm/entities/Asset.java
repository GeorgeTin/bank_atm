package com.bank.atm.entities;

import com.bank.atm.helpers.Currency;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    private Double balance;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public Asset() {}

    public Asset(@NotNull Currency currency, @NotNull Double balance,
                 @NonNull BankAccount bankAccount) {
        this.currency = currency;
        this.balance = balance;
        this.bankAccount = bankAccount;
    }
}

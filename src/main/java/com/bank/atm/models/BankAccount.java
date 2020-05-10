package com.bank.atm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Bank bank;

    @NotNull
    private String version;
}

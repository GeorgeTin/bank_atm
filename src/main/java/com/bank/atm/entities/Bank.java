package com.bank.atm.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter  @Setter
@RequiredArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    private String bic;

    public Bank(@NotNull String name, Address address, @NotNull String bic) {
        this.name = name;
        this.address = address;
        this.bic = bic;
    }
}

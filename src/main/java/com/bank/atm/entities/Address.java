package com.bank.atm.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String county;

    @NotNull
    private String locality;

    @NotNull
    private String exactAddress;

    @NotNull
    private String postalCode;

    public Address(@NotNull String county, @NotNull String locality,
                   @NotNull String exactAddress, @NotNull String postalCode) {
        this.county = county;
        this.locality = locality;
        this.exactAddress = exactAddress;
        this.postalCode = postalCode;
    }
}

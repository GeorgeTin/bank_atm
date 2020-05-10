package com.bank.atm.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Address address;

    @OneToMany(mappedBy="branch", fetch= FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ATM> atms;
}

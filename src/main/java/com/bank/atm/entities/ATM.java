package com.bank.atm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_id")
    private Branch branch;

    @NotNull
    private String version;
}

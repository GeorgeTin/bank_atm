package com.bank.atm.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CardDTO {
    private String cardNumber;
    private Timestamp issueDate;
    private Timestamp expiryDate;
    private Long issuingBank;
    private String issuingAgency;
    private Long bankAccount;
}

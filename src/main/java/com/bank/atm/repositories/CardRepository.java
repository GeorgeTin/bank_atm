package com.bank.atm.repositories;

import com.bank.atm.entities.Card;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findCardByCardNumberAndIssueDateAndExpiryDateAndIssuingBankIdAndIssuingAgencyAndBankAccountId(
            @NotNull String cardNumber, @NotNull Timestamp issueDate, @NotNull Timestamp expiryDate,
            @NotNull Long issuingBank, @NotNull String issuingAgency, @NotNull Long bankAccount);
}

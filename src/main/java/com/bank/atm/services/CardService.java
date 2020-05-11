package com.bank.atm.services;

import com.bank.atm.dtos.CardDTO;
import com.bank.atm.entities.Card;

import java.util.Optional;

public interface CardService {
    Optional<Card> findById(Long id);
    Optional<Card> findByCardInfo(CardDTO cardDTO);
}

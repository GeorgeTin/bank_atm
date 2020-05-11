package com.bank.atm.services.impl;

import com.bank.atm.dtos.CardDTO;
import com.bank.atm.entities.Card;
import com.bank.atm.repositories.CardRepository;
import com.bank.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    /**
     * Looks for a registered card based on the information stored on the physical card.
     * @param cardDTO
     * @return
     */
    @Override
    public Optional<Card> findByCardInfo(CardDTO cardDTO) {
        return cardRepository.findCardByCardNumberAndIssueDateAndExpiryDateAndIssuingBankIdAndIssuingAgencyAndBankAccountId(
                cardDTO.getCardNumber(), cardDTO.getIssueDate(), cardDTO.getExpiryDate(),
                cardDTO.getIssuingBank(), cardDTO.getIssuingAgency(), cardDTO.getBankAccount());
    }
}

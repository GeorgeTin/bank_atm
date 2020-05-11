package com.bank.atm.repositories;

import com.bank.atm.helpers.Currency;
import com.bank.atm.entities.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends CrudRepository<Asset, Long> {

    Optional<Asset> findByBankAccountIdAndCurrency(Long id, Currency currency);
    List<Asset> findAllByBankAccountId(Long id);
}

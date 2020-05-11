package com.bank.atm.services.impl;

import com.bank.atm.entities.ATM;
import com.bank.atm.errors.NotEnoughAssetsException;
import com.bank.atm.helpers.Currency;
import com.bank.atm.entities.Asset;
import com.bank.atm.entities.BankAccount;
import com.bank.atm.repositories.ATMRepository;
import com.bank.atm.repositories.AssetRepository;
import com.bank.atm.services.ATMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ATMServiceImpl implements ATMService {

    private AssetRepository assetRepository;
    private ATMRepository atmRepository;

    private Logger logger = LoggerFactory.getLogger(ATMServiceImpl.class);

    @Autowired
    public ATMServiceImpl(AssetRepository assetRepository, ATMRepository atmRepository) {
        this.assetRepository = assetRepository;
        this.atmRepository = atmRepository;
    }

    @Override
    public void withdraw(BankAccount bankAccount, Double amount, Currency currency) throws NotEnoughAssetsException {
        Optional<Asset> maybeAsset = assetRepository.findByBankAccountIdAndCurrency(bankAccount.getId(), currency);
        maybeAsset.orElseThrow(NotEnoughAssetsException::new);

        logger.info("Withdrawing money.");
        Asset asset = maybeAsset.get();
        if(asset.getBalance().compareTo(amount) >= 0) {
            asset.setBalance(asset.getBalance() - amount);
            assetRepository.save(asset);
        }
    }

    @Override
    public void deposit(BankAccount bankAccount, Double amount, Currency currency) {
        Optional<Asset> maybeAsset = assetRepository.findByBankAccountIdAndCurrency(bankAccount.getId(), currency);
        logger.info("Deposit money");
        Asset asset = maybeAsset.orElseGet(() -> new Asset(currency, 0.0, bankAccount));
        asset.setBalance(asset.getBalance() + amount);
        assetRepository.save(asset);
    }

    @Override
    public Map<Currency, Double> interrogate(BankAccount bankAccount) {
        List<Asset> assetList = assetRepository.findAllByBankAccountId(bankAccount.getId());
        logger.info("Interrogating sold");
        return assetList
                .parallelStream()
                .filter(asset -> asset.getBalance().compareTo(0.0) != 0)
                .collect(Collectors.toMap(Asset::getCurrency, Asset::getBalance));
    }

    @Override
    public Optional<ATM> findById(Long id) {
        return atmRepository.findById(id);
    }
}

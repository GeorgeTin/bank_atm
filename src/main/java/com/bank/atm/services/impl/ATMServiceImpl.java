package com.bank.atm.services.impl;

import com.bank.atm.errors.NotEnoughAssetsException;
import com.bank.atm.helpers.Currency;
import com.bank.atm.entities.Asset;
import com.bank.atm.entities.BankAccount;
import com.bank.atm.repositories.AssetRepository;
import com.bank.atm.services.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ATMServiceImpl implements ATMService {

    private AssetRepository assetRepository;

    @Autowired
    public ATMServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public void withdraw(BankAccount bankAccount, Double amount, Currency currency) throws NotEnoughAssetsException {
        Optional<Asset> maybeAsset = assetRepository.findByBankAccountIdAndCurrency(bankAccount.getId(), currency);
        maybeAsset.orElseThrow(NotEnoughAssetsException::new);

        Asset asset = maybeAsset.get();
        if(asset.getBalance().compareTo(amount) >= 0) {
            asset.setBalance(asset.getBalance() - amount);
            assetRepository.save(asset);
        }
    }

    @Override
    public void deposit(BankAccount bankAccount, Double amount, Currency currency) {
        Optional<Asset> maybeAsset = assetRepository.findByBankAccountIdAndCurrency(bankAccount.getId(), currency);
        Asset asset = maybeAsset.orElseGet(() -> new Asset(currency, 0.0, bankAccount));
        asset.setBalance(asset.getBalance() + amount);
        assetRepository.save(asset);
    }

    @Override
    public Map<Currency, Double> interrogate(BankAccount bankAccount) {
        List<Asset> assetList = assetRepository.findAllByBankAccountId(bankAccount.getId());
        return assetList
                .parallelStream()
                .filter(asset -> asset.getBalance().compareTo(0.0) > 0)
                .collect(Collectors.toMap(Asset::getCurrency, Asset::getBalance));
    }
}

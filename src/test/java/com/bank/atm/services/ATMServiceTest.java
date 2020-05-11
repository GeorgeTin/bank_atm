package com.bank.atm.services;

import com.bank.atm.entities.*;
import com.bank.atm.helpers.Currency;
import com.bank.atm.repositories.ATMRepository;
import com.bank.atm.repositories.AssetRepository;
import com.bank.atm.services.impl.ATMServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class ATMServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private ATMRepository atmRepository;

    private ATMService atmService;

    @Before
    public void setUp() {
        initMocks(this);
        atmService = new ATMServiceImpl(assetRepository, atmRepository);

        Address address = new Address("Romania", "Bucharest", "Str. Victoriei", "123123");
        Bank bank = new Bank("ING", address, "bic");
        BankAccount ba = new BankAccount(1L, "12341234123451234", "123123", bank, null);
        List<Asset> assets = new ArrayList<>(Arrays.asList(
                new Asset(Currency.EURO, 23.0, ba),
                new Asset(Currency.EURO, 0.0, ba),
                new Asset(Currency.DOLLAR, -3.0, ba)
        ));

        Mockito.when(assetRepository.findAllByBankAccountId(ba.getId()))
                .thenReturn(assets);
    }

    @Test
    public void whenInterrogate_thenFindAssets() {
        Address address = new Address("Romania", "Bucharest", "Str. Victoriei", "123123");
        Bank bank = new Bank("ING", address, "bic");
        BankAccount ba = new BankAccount(1L, "12341234123451234", "123123", bank, null);

        Map<Currency, Double> map = atmService.interrogate(ba);
        Assert.assertTrue(map.size() > 0);
    }

    @Test
    public void whenInterrogate_thenFindAssetsWithNonZeroBalance() {
        Address address = new Address("Romania", "Bucharest", "Str. Victoriei", "123123");
        Bank bank = new Bank("ING", address, "bic");
        BankAccount ba = new BankAccount(1L, "12341234123451234", "123123", bank, null);

        Map<Currency, Double> map = atmService.interrogate(ba);
        for(Double val : map.values())
            Assert.assertTrue(val != 0.0);
    }
}

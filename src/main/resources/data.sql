INSERT INTO ADDRESS (id, county, locality, exact_address, postal_code) VALUES (1, 'Bucuresti', 'Sector 1', 'strada Aviator Popișteanu, nr. 54A, clădirea nr. 3', '12312');
INSERT INTO BANK(id, name, address_id, bic) VALUES (1, 'ING', 1, 'INGBNL2A');
INSERT INTO BANK_ACCOUNT(id, iban, account_number, bank_id, card_id) VALUES (1, 'AD1400080001001234567890', '123123123', 1, null);
INSERT INTO CARD (id, card_number, issue_date, expiry_date, bank_id, issuing_agency, bank_account_id) VALUES (1, '1234123412341234', '2019-02-23T20:02:21.550', '2019-02-23T20:02:21.550', 1, 'ING', 1);
UPDATE BANK_ACCOUNT SET card_id = 1 WHERE id = 1;
INSERT INTO ASSET(id, currency, balance, bank_account_id) VALUES(1, 'EURO', 12.12, 1);
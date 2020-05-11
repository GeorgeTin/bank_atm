package com.bank.atm.repositories;

import com.bank.atm.entities.ATM;
import org.springframework.data.repository.CrudRepository;

public interface ATMRepository extends CrudRepository<ATM, Long> {
}

package com.aninfo.model;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositZeroSumException;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Deposit extends Transaction{

    public Deposit() {super();}
    public Deposit(double amount, Account account) {
        super(amount, account);
    }

}

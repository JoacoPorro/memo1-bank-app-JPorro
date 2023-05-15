package com.aninfo.model;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;

import javax.persistence.Entity;

@Entity
public class Withdraw extends Transaction {


    public Withdraw() {super();}
    public Withdraw(double amount, Account account) {
        super(amount, account);
    }

}

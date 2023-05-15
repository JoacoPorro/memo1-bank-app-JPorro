package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositZeroSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Deposit;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class DepositService extends TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    public Deposit createDeposit(double amount, Account account) {

        if (amount < 0) {
            throw new DepositNegativeSumException("Negative Deposit");
        } else if (amount == 0) {
            throw new DepositZeroSumException("Deposit Amount is zero");
        }

        if (amount >= 2000.0 ){
            if  (amount * 0.1 <= 500.0) { amount = amount + amount * 0.1; }
            else { amount = amount + 500; }
        }

        Deposit newDeposit =  new Deposit(amount, account);
        transactionRepository.save(newDeposit);
        return newDeposit;
    }

}

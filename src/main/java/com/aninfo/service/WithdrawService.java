package com.aninfo.service;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Withdraw;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class WithdrawService extends TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    public Withdraw createWithdraw(double amount, Account account) {

        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Negative Deposit");
        }

        Withdraw newWithdraw =  new Withdraw(amount, account);
        transactionRepository.save(newWithdraw);
        return newWithdraw;
    }

}

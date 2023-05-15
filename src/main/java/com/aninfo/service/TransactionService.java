package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }

    public double getTransactionCount() {
        return (double) transactionRepository.findAll().size();
    }
}

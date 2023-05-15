package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Transaction findTransactionByCode(Long code);

    List<Transaction> findAllByAccount(Account account);
    @Override
    List<Transaction> findAll();
}

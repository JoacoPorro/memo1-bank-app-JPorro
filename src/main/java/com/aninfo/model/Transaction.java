package com.aninfo.model;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Optional;

@Entity
public class Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    private Double amount;

    public Long getCode() { return code; }
    public Double getAmount() { return amount; }
    public Account getAccount() { return account; }

    public Transaction(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public Transaction() {
        this.amount = 1000.0;
        this.account = new Account();
    }
}

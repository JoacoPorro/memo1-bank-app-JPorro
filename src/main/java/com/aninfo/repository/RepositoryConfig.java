package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.Deposit;
import com.aninfo.model.Withdraw;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Account.class, Transaction.class, Deposit.class, Withdraw.class);
    }


}

package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Deposit;
import com.aninfo.model.Withdraw;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private DepositService depositService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }
    public Account findAnAccountByCbu(Long cbu) { return accountRepository.findAccountByCbu(cbu);}

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        Withdraw newWithdraw = withdrawService.createWithdraw(sum, account);

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        Deposit newDeposit = depositService.createDeposit(sum, account);

        account.setBalance(account.getBalance() + newDeposit.getAmount());
        accountRepository.save(account);

        return account;
    }

}

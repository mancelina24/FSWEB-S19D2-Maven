package com.workintech.s18d4.service;


import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent())
        {
            return optionalAccount.get();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        Account updatedAccount = find(id);
        updatedAccount.setAccountName(account.getAccountName());
        updatedAccount.setId(account.getId());
        updatedAccount.setCustomer(account.getCustomer());
        updatedAccount.setMoneyAmount(account.getMoneyAmount());
        return accountRepository.save(updatedAccount);
    }

    @Override
    public Account delete(Long id) {
        Account account = find(id);
        accountRepository.delete(account);
        return account;
    }
}

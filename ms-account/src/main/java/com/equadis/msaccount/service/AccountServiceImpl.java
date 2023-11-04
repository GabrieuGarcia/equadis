package com.equadis.msaccount.service;

import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.repository.AccountRepository;
import com.equadis.msaccount.utils.AccountConverter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Account save(Account account) {
        var accountSaved = this.repository.save(AccountConverter.modelToEntity(account));
        return AccountConverter.entityToModel(accountSaved);
    }
}

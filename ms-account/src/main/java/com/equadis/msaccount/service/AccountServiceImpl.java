package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.producer.AccountProducer;
import com.equadis.msaccount.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountProducer accountProducer;
    private final AccountRepository repository;

    public AccountServiceImpl(AccountProducer accountProducer, AccountRepository repository) {
        this.accountProducer = accountProducer;
        this.repository = repository;
    }

    @Override
    @Transactional
    public Account save(Account account) throws AccountException {

        Object obj = this.accountProducer.publishMessageCustomerValidation(account);

        System.out.println(obj);

        /*

        AccountValidator.creation(account);

        var accountSaved = this.repository.save(AccountConverter.modelToEntity(account));

        return AccountConverter.entityToModel(accountSaved);

         */

        return null;
    }
}

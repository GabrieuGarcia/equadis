package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.producer.AccountProducer;
import com.equadis.msaccount.repository.AccountRepository;
import com.equadis.msaccount.utils.AccountConstants;
import com.equadis.msaccount.utils.AccountConverter;
import com.equadis.msaccount.validator.AccountValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        this.getAndSetAccountCustomer(account);

        AccountValidator.creation(account);
        var accountSaved = this.repository.save(AccountConverter.modelToEntity(account));

        return AccountConverter.entityToModel(accountSaved);
    }

    @Override
    public Account findById(UUID accountId) {
        return AccountConverter.entityToModel(this.repository.findById(accountId).get());
    }

    private void getAndSetAccountCustomer(Account account) throws AccountException {
        if(account.getCustomerId() == null) {
            return;
        }

        try {
            //Calls MS-Customer
            var customer =  this.accountProducer.publishMessageCustomerValidation(account);
            account.setCustomerId(customer == null ? null : customer.getCustomerId());
        } catch (Exception e) {
            throw new AccountException(AccountConstants.MSG_NOT_RECEIVE_CUSTOMER);
        }
    }
}

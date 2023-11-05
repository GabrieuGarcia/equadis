package com.equadis.msaccount.service;

import com.equadis.msaccount.enums.TransactionType;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Transaction;
import com.equadis.msaccount.repository.TransactionRepository;
import com.equadis.msaccount.utils.TransactionConverter;
import com.equadis.msaccount.validator.TransactionValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }


    @Override
    @Transactional
    public Transaction deposit(Transaction transaction) throws TransactiontException, AccountException {

        this.buildDeposit(transaction);

        TransactionValidator.depositValidations(transaction);

        BigDecimal newAccountValue = transaction.getAccount().getAccountAmount().add(transaction.getTransactionAmount());
        transaction.getAccount().setAccountAmount(newAccountValue);
        this.accountService.save(transaction.getAccount());

        return this.save(transaction);
    }

    @Override
    @Transactional
    public Transaction withdraw(Transaction transaction) throws TransactiontException, AccountException {
        this.buildWithdraw(transaction);
        TransactionValidator.withdrawValidations(transaction);

        BigDecimal newAccountValue = transaction.getAccount().getAccountAmount().subtract(transaction.getTransactionAmount());
        transaction.getAccount().setAccountAmount(newAccountValue);
        this.accountService.save(transaction.getAccount());

        return this.save(transaction);
    }

    @Override
    public Transaction save(Transaction transaction) {
        var response = this.transactionRepository.save(TransactionConverter.modelToEntity(transaction));
        return TransactionConverter.entityToModel(response);
    }

    private void buildDeposit(Transaction transaction) {
        this.getAndSetTransactionAccount(transaction);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(TransactionType.DEPOSIT);
    }

    private void buildWithdraw(Transaction transaction) {
        this.getAndSetTransactionAccount(transaction);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(TransactionType.WITHDRAW);

        if(transaction.getTransactionAmount() != null) {
            transaction.getTransactionAmount().abs();
        }
    }

    private void getAndSetTransactionAccount(Transaction transaction) {
        if(transaction.getAccount() == null) {
            return;
        }

        var account = this.accountService.findById(transaction.getAccount().getAccountId());
        transaction.setAccount(account);
    }
}

package com.equadis.msaccount.service;

import com.equadis.msaccount.enums.TransactionType;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Transaction;
import com.equadis.msaccount.repository.TransactionRepository;
import com.equadis.msaccount.utils.TransactionConverter;
import com.equadis.msaccount.utils.Utils;
import com.equadis.msaccount.validator.TransactionValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public Transaction deposit(Transaction transaction) throws TransactiontException, AccountException, ParseException {

        this.buildDeposit(transaction);

        TransactionValidator.depositValidations(transaction);

        BigDecimal newAccountValue = transaction.getAccount().getAccountAmount().add(transaction.getTransactionAmount());
        transaction.getAccount().setAccountAmount(newAccountValue);
        this.accountService.save(transaction.getAccount());

        return this.save(transaction);
    }

    @Override
    @Transactional
    public Transaction withdraw(Transaction transaction) throws TransactiontException, AccountException, ParseException {
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

    @Override
    public List<Transaction> findAll() {
        return TransactionConverter.entitiesToModels(this.transactionRepository.findAll());
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(final UUID accountId) {
        return TransactionConverter.entitiesToModels(this.transactionRepository.findAllByAccountAccountId(accountId));
    }

    @Override
    public List<Transaction> findBetweenAmounts(final BigDecimal firstAmount, final BigDecimal SecondAmount) {
        return TransactionConverter.entitiesToModels(this.transactionRepository.findBetweenAmounts(firstAmount,SecondAmount));
    }

    @Override
    public List<Transaction> findBetweenDates(final Date firstDate, final Date secondDate) {
        return TransactionConverter.entitiesToModels(this.transactionRepository.findBetweenDates(firstDate, secondDate));
    }

    private void buildDeposit(final Transaction transaction) throws ParseException {
        this.getAndSetTransactionAccount(transaction);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionDate(new Date());
    }

    private void buildWithdraw(final Transaction transaction) throws ParseException {
        this.getAndSetTransactionAccount(transaction);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTransactionDate(new Date());

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

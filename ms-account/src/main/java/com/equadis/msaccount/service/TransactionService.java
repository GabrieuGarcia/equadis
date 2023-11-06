package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction deposit(Transaction transaction) throws TransactiontException, AccountException, ParseException;

    Transaction withdraw(Transaction transaction) throws TransactiontException, AccountException, ParseException;

    Transaction save(Transaction transaction) throws TransactiontException;

    List<Transaction> findAll();

    List<Transaction> findTransactionsByAccountId(UUID accountId);

    List<Transaction> findBetweenAmounts(final BigDecimal firstAmount, final BigDecimal SecondAmount);

    List<Transaction> findBetweenDates(final Date firstDate, final Date endDate);

}

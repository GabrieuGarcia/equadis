package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Transaction;

public interface TransactionService {

    Transaction deposit(Transaction transaction) throws TransactiontException, AccountException;

    Transaction withdraw(Transaction transaction) throws TransactiontException, AccountException;

    Transaction save(Transaction transaction) throws TransactiontException;

}

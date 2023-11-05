package com.equadis.msaccount.validator;

import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Transaction;
import com.equadis.msaccount.utils.TransactionsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.equadis.msaccount.utils.Utils.buildErrorMessage;

public class TransactionValidator {

    public static void depositValidations(Transaction transaction) throws TransactiontException {
        List<String> validations = new ArrayList<>();

        validateAccountsTransaction(transaction, validations);
        validateDepositAmount(transaction, validations);
        validateAmount(transaction, validations);

        if(!validations.isEmpty()) {
            throw new TransactiontException(buildErrorMessage(validations));
        }
    }

    public static void withdrawValidations(Transaction transaction) throws TransactiontException {
        List<String> validations = new ArrayList<>();

        validateAccountsTransaction(transaction, validations);
        validateAmount(transaction, validations);
        validateWithdrawPossibility(transaction, validations);

        if(!validations.isEmpty()) {
            throw new TransactiontException(buildErrorMessage(validations));
        }
    }

    private static void validateDepositAmount(Transaction transaction, List<String> validations) {
        if(transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) < 0) {
            validations.add(TransactionsConstants.MSG_BAD_AMOUNT_DEPOSIT);
        }
    }

    private static void validateAccountsTransaction(Transaction transaction, List<String> validations) {
        if(transaction.getAccount() == null || transaction.getAccount().getAccountId() == null) {
            validations.add(TransactionsConstants.MSG_NO_ACCOUNT);
        }
    }

    private static void validateAmount(Transaction transaction, List<String> validations) {
        if(transaction.getAccount() == null || transaction.getAccount().getAccountId() == null || transaction.getAccount().getAccountAmount() == null) {
            return;
        }

        BigDecimal accountFinalValue = transaction.getAccount().getAccountAmount().add(transaction.getTransactionAmount());

        if(accountFinalValue.compareTo(BigDecimal.ZERO) < 0) {
            validations.add(TransactionsConstants.MSG_BAD_AMOUNT);
        }
    }

    private static void validateWithdrawPossibility(Transaction transaction, List<String> validations) {
        if(transaction.getAccount() == null || transaction.getAccount().getAccountId() == null || transaction.getAccount().getAccountAmount() == null) {
            return;
        }

        BigDecimal accountFinalValue = transaction.getAccount().getAccountAmount().subtract(transaction.getTransactionAmount());

        if(accountFinalValue.compareTo(BigDecimal.ZERO) < 0) {
            validations.add(TransactionsConstants.MSG_BAD_AMOUNT);
        }
    }



}

package com.equadis.msaccount.enpoint;

import com.equadis.msaccount.enums.TransactionType;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.model.Transaction;
import com.equadis.msaccount.service.TransactionService;
import com.equadis.msaccount.utils.TransactionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionEndpointTest {

    @InjectMocks
    private TransactionEndpoint endpoint;

    @Mock
    private TransactionService transactionService;

    private Account buildAccount() {
        var account = new Account();
        account.setAccountAmount(new BigDecimal(10000));
        account.setCustomerId(UUID.randomUUID());
        return account;
    }

    private Transaction buildTransaction() {
        var transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionAmount(new BigDecimal(10000));
        transaction.setAccount(buildAccount());
        return transaction;
    }


    @Test
    void depositTest() throws AccountException, TransactiontException, ParseException {
        //Given
        final var transaction = buildTransaction();
        var dto = TransactionConverter.modelToRecordDto(transaction);
        when(transactionService.deposit(any())).thenReturn(transaction);

        //When
        var result = this.endpoint.deposit(dto);

        //Then
        verify(transactionService, atLeast(1)).deposit(any());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void withdrawTest() throws AccountException, TransactiontException, ParseException {
        //Given
        final var transaction = buildTransaction();
        var dto = TransactionConverter.modelToRecordDto(transaction);
        when(transactionService.deposit(any())).thenReturn(transaction);

        //When
        var result = this.endpoint.deposit(dto);

        //Then
        verify(transactionService, atLeast(1)).deposit(any());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void findAllTest() {
        //Given
        final List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(buildTransaction());
        when(transactionService.findAll()).thenReturn(transactionsList);

        //When
        var result = this.endpoint.findAll();

        //Then
        verify(transactionService, atLeast(1)).findAll();
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void findAllByAccountIdTest() {
        //Given
        var id = UUID.randomUUID();
        final List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(buildTransaction());
        when(transactionService.findTransactionsByAccountId(any())).thenReturn(transactionsList);

        //When
        var result = this.endpoint.findAllByAccountId(id);

        //Then
        verify(transactionService, atLeast(1)).findTransactionsByAccountId(id);
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void findAllByAmountsTest() {
        //Given
        final List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(buildTransaction());
        when(transactionService.findBetweenAmounts(any(), any())).thenReturn(transactionsList);

        //When
        var result = this.endpoint.findAllByAmounts(any(), any());

        //Then
        verify(transactionService, atLeast(1)).findBetweenAmounts(any(), any());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void findAllByDates() throws ParseException {
        String fistDate = "2023-10-01T00:00:00.000+00:00";
        String secontDate = "2024-11-01T00:00:00.000+00:00";

        //Given
        final List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(buildTransaction());
        when(transactionService.findBetweenDates(any(), any())).thenReturn(transactionsList);

        //When
        var result = this.endpoint.findAllByDates(fistDate, secontDate);

        //Then
        verify(transactionService, atLeast(1)).findBetweenDates(any(), any());
        Assertions.assertNotNull(result.getBody());
    }
}
package com.equadis.msaccount.model;

import com.equadis.msaccount.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Transaction {

    private UUID transactionId;
    private Account account;
    private BigDecimal transactionAmount;
    private Date transactionDate;
    private TransactionType transactionType;

}

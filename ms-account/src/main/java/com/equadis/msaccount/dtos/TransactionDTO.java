package com.equadis.msaccount.dtos;

import com.equadis.msaccount.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDTO {

    private UUID transactionId;
    private AccountRecordDto account;
    private BigDecimal transactionAmount;
    private Date transactionDate;
    private TransactionType transactionType;
}

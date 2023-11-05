package com.equadis.msaccount.dtos;

import com.equadis.msaccount.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record TransactionRecordDTO(
        UUID transactionId,
        AccountRecordDto account,
        BigDecimal transactionAmount,
        Date transactionDate,
        TransactionType transactionType) {
}

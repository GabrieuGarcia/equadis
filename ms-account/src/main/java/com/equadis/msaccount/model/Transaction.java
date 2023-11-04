package com.equadis.msaccount.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class Transaction {

    private UUID transactionId;
    private List<Account> accounts;
    private BigDecimal transactionAmount;

}

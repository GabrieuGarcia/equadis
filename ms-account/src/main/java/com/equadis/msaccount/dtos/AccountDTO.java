package com.equadis.msaccount.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class AccountDTO {

    private UUID accountId;
    private UUID customerId;
    private BigDecimal accountAmount;
    private List<TransactionDTO> transactions;
}

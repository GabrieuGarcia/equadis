package com.equadis.msaccount.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Account {

    private UUID accountId;
    private UUID customerId;
    private BigDecimal accountAmount;

}

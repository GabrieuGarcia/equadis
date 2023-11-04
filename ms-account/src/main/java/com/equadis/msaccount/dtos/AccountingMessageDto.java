package com.equadis.msaccount.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountingMessageDto {

    private UUID accountId;
    private UUID customerId;

}

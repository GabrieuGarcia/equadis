package com.equadis.msaccount.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRecordDto(UUID accountId, UUID customerId, BigDecimal accountAmount) {
}

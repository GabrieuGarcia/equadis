package com.equadis.msaccount.dtos;

import java.util.UUID;

public record CustomerRecordDto(UUID customerId, String customerName) {
}

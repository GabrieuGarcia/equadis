package com.equadis.mscustomer.dto;

import java.util.UUID;

public record CustomerRecordDto(UUID customerId, String customerName) {
}

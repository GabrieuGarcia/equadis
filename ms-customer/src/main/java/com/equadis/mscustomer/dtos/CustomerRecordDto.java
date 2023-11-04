package com.equadis.mscustomer.dtos;

import java.util.UUID;

public record CustomerRecordDto(UUID customerId, String customerName) {
}

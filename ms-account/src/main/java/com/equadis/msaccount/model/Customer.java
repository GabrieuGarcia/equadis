package com.equadis.msaccount.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {

    private UUID customerId;
    private String customerName;
}

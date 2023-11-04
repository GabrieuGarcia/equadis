package com.equadis.mscustomer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CUSTOMER")
public class ECustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

}

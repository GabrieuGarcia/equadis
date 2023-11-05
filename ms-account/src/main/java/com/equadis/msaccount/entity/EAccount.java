package com.equadis.msaccount.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name ="TB_ACCOUNT")
public class EAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @Column(name = "CUSTOMER_ID")
    private UUID customerId;

    @Column(name = "ACCOUNT_AMOUNT")
    private BigDecimal accountAmount;

    @OneToMany
    private List<ETransaction> transactions;

}

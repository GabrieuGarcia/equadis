package com.equadis.msaccount.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_TRANSACTION")
public class ETransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;


    @ManyToMany(mappedBy = "transactions")
    private List<EAccount> accounts;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

}

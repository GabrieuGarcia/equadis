package com.equadis.msaccount.repository;

import com.equadis.msaccount.entity.EAccount;
import com.equadis.msaccount.entity.ETransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<ETransaction, UUID> {

    List<ETransaction> findAllByAccountAccountId(UUID accountId);


    @Query("SELECT tr FROM ETransaction tr WHERE tr.transactionAmount BETWEEN :firstAmount AND :secondAmount")
    List<ETransaction> findBetweenAmounts(@Param("firstAmount") final BigDecimal firstAmount,
                                          @Param("secondAmount") final BigDecimal secondAmount);

    @Query("SELECT tr FROM ETransaction tr WHERE tr.transactionDate BETWEEN :firstDate AND :secondDate")
    List<ETransaction> findBetweenDates(@Param("firstDate") final Date firstDate,
                                          @Param("secondDate") final Date secondDate);
}

package com.equadis.msaccount.repository;

import com.equadis.msaccount.entity.EAccount;
import com.equadis.msaccount.entity.ETransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<ETransaction, UUID> {
}

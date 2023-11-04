package com.equadis.msaccount.repository;

import com.equadis.msaccount.entity.EAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<EAccount, UUID> {
}

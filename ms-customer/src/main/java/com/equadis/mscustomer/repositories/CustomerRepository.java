package com.equadis.mscustomer.repositories;

import com.equadis.mscustomer.entities.ECustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<ECustomer, UUID> {

}

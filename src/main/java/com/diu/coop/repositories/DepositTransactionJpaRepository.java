package com.diu.coop.repositories;

import com.diu.coop.model.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositTransactionJpaRepository extends JpaRepository<DepositTransaction, Integer> {
}

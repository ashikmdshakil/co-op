package com.diu.coop.repositories;

import com.diu.coop.model.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionJpaRepository extends JpaRepository<LoanTransaction, Integer> {
}

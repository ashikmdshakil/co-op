package com.diu.coop.repositories;

import com.diu.coop.model.LoanOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanOptionJpaRepository extends JpaRepository<LoanOptionJpaRepository, Integer> {
    LoanOption findById(int id);
}

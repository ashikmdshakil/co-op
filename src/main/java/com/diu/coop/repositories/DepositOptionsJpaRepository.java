package com.diu.coop.repositories;

import com.diu.coop.model.DepositOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositOptionsJpaRepository extends JpaRepository<DepositOptions, Integer> {
    DepositOptions findById(int id);
}

package com.diu.coop.repositories;

import com.diu.coop.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositJPARepository extends JpaRepository<Deposit, Integer> {

    Deposit findById(int id);
    List<Deposit> findByUserUserId(int id);
}

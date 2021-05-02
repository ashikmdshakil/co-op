package com.diu.coop.repositories;

import com.diu.coop.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanJPARepository extends JpaRepository<Loan, Integer> {
    Loan findById(int id);
    List<Loan> findByUserUserId(int id);
}

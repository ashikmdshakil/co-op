package com.diu.coop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Component
@Entity
@Scope(scopeName = "prototype")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float targetAmount;
    private float totalAmount;
    private int totalMonths;
    private LocalDateTime lastInstallmentDate;
    @ManyToOne
    private Users user;
    @OneToMany(mappedBy = "loan")
    @JsonIgnoreProperties("loan")
    private List<LoanTransaction> loanTransactions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalMonths() {
        return totalMonths;
    }

    public void setTotalMonths(int totalMonths) {
        this.totalMonths = totalMonths;
    }

    public LocalDateTime getLastInstallmentDate() {
        return lastInstallmentDate;
    }

    public void setLastInstallmentDate(LocalDateTime lastInstallmentDate) {
        this.lastInstallmentDate = lastInstallmentDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<LoanTransaction> getLoanTransactions() {
        return loanTransactions;
    }

    public void setLoanTransactions(List<LoanTransaction> loanTransactions) {
        this.loanTransactions = loanTransactions;
    }
}

package com.diu.coop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Scope(scopeName = "prototype")
public class Deposite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalAmount;
    private int totalMonths;
    private LocalDateTime firstInstallmentDate;
    private LocalDateTime lastInstallmentDate;
    @ManyToOne
    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalMonths() {
        return totalMonths;
    }

    public void setTotalMonths(int totalMonths) {
        this.totalMonths = totalMonths;
    }

    public LocalDateTime getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(LocalDateTime firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
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
}

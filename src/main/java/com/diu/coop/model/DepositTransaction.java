package com.diu.coop.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
@Component
@Entity
public class DepositTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float amount;
    private LocalDateTime time;
    @ManyToOne
    private Deposit deposit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
}

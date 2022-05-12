package com.backendchallenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "withdraws")
public class Withdraw implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Integer id;
    @Column(name="withdraw_value", columnDefinition="Decimal(10,2)")
    private double withdrawValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Withdraw() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(double withdrawValue) {
        this.withdrawValue = withdrawValue;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Withdraw withdraw = (Withdraw) o;
        return id.equals(withdraw.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

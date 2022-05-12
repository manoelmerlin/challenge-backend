package com.backendchallenge.challenge.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Transient
    double defaultAccountBalance = 1000;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Withdraw> withdraws = new ArrayList<>();

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    public double getAccountBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Withdraw withdraw: withdraws) {
            defaultAccountBalance -= withdraw.getWithdrawValue();
        }

        return defaultAccountBalance;
    }
}

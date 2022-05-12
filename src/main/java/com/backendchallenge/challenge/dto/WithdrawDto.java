package com.backendchallenge.challenge.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class WithdrawDto implements Serializable {

    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 1, message = "The minimum withdrawal amount is $1")
    @Max(value = 300, message = "You cannot withdraw more than $300")
    private Double withdrawValue;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(Double withdrawValue) {
        this.withdrawValue = withdrawValue;
    }
}

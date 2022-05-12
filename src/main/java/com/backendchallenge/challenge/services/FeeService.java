package com.backendchallenge.challenge.services;

public class FeeService {
    private double value;

    public FeeService(double value) {
        this.value = value;
    }

    public int getFeeValue() {
        if (value < 101) {
            return 3;
        } else if (value >= 101 && value < 251) {
            return 2;
        }

        return 1;
    }

    public double calculateValueWithFee() {
        return (value * getFeeValue() / 100) + value;
    }
}

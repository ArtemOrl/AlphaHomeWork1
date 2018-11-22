package com.alpha.RailWay;

public class Bill {
    int id;
    long amount;

    public Bill(long amount) {
        this.amount = amount;
        this.id = 0 + (int)(Math.random() * 100);
    }
}

package com.atguigu.bofei;

/**
 * @description:
 * @author: bofei
 * @date: 2024-07-05 15:25
 **/

public class Transaction {
    private String type;
    private double amount;
    private long timestamp;

    public Transaction(String type, double amount, long timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

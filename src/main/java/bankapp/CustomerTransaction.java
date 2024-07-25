package bankapp;

import java.sql.Timestamp;

public class CustomerTransaction {
    private int transactionId;
    private int customerAccNo;
    private Timestamp transactionDate;
    private String transactionType;
    private double amount;

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerAccNo() {
        return customerAccNo;
    }

    public void setCustomerAccNo(int customerAccNo) {
        this.customerAccNo = customerAccNo;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

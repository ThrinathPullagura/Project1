package bankapp;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private double amount;
    private String transactionType;
    private Date transactionDate;

    public Transaction(String transactionId, double amount, String transactionType, Date transactionDate) {
        this.setTransactionId(transactionId);
        this.setAmount(amount);
        this.setTransactionType(transactionType);
        this.setTransactionDate(transactionDate);
    }

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

    // Getters and Setters
}

package com.example.expensetracker.logic;

public class ParsedData {
    public double amount;
    public String type; // "DEBIT" or "CREDIT"
    public String merchant;
    public String referenceId;
    public String paymentMethod;
    
    public ParsedData(double amount, String type, String merchant, String referenceId, String paymentMethod) {
        this.amount = amount;
        this.type = type;
        this.merchant = merchant;
        this.referenceId = referenceId;
        this.paymentMethod = paymentMethod;
    }
}

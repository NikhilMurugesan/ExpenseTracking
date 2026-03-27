package com.example.expensetracker.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions",
        indices = {
                @Index(value = {"date", "amount", "merchantName"}, unique = true),
                @Index(value = "categoryId"),
                @Index(value = "contactId")
        },
        foreignKeys = {
                @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId"),
                @ForeignKey(entity = ContactLedger.class, parentColumns = "id", childColumns = "contactId")
        })
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double amount;
    public String type; // DEBIT / CREDIT
    public long date;
    public String merchantName;
    public String paymentMethod;
    public String referenceId;
    public Integer categoryId;
    public Integer contactId;
}

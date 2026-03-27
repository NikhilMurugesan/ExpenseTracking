package com.example.expensetracker.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_ledger")
public class ContactLedger {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String phoneNumber;
    public String upiId;
    public double totalGiven;
    public double totalReceived;
}

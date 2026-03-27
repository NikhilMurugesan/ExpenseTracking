package com.example.expensetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.expensetracker.data.entity.ContactLedger;

import java.util.List;

@Dao
public interface ContactLedgerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(ContactLedger ledger);

    @Update
    void update(ContactLedger ledger);

    @Query("SELECT * FROM contact_ledger WHERE upiId = :upiId LIMIT 1")
    ContactLedger getByUpiId(String upiId);

    @Query("SELECT * FROM contact_ledger")
    LiveData<List<ContactLedger>> getAllLedgers();
}

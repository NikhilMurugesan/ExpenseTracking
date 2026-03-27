package com.example.expensetracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.expensetracker.data.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Transaction transaction);

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'DEBIT' AND date >= :startDate")
    LiveData<Double> getExpensesSince(long startDate);
    
    @Query("SELECT * FROM transactions")
    List<Transaction> getAllSynchronous(); // For backup worker
    
    @Query("SELECT categoryId, SUM(amount) as total FROM transactions WHERE type = 'DEBIT' GROUP BY categoryId")
    LiveData<List<com.example.expensetracker.data.entity.CategorySum>> getCategorySums();
}

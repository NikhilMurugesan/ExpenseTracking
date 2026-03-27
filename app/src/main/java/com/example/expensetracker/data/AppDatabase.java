package com.example.expensetracker.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensetracker.data.dao.CategoryDao;
import com.example.expensetracker.data.dao.ContactLedgerDao;
import com.example.expensetracker.data.dao.TransactionDao;
import com.example.expensetracker.data.entity.Category;
import com.example.expensetracker.data.entity.ContactLedger;
import com.example.expensetracker.data.entity.Transaction;

@Database(entities = {Transaction.class, Category.class, ContactLedger.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract TransactionDao transactionDao();
    public abstract CategoryDao categoryDao();
    public abstract ContactLedgerDao contactLedgerDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "expense_tracker_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

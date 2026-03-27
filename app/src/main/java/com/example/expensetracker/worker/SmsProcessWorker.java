package com.example.expensetracker.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.expensetracker.data.AppDatabase;
import com.example.expensetracker.data.entity.Category;
import com.example.expensetracker.data.entity.Transaction;
import com.example.expensetracker.logic.ParsedData;
import com.example.expensetracker.logic.SmsParser;
import com.example.expensetracker.logic.TransactionClassifier;

import java.util.List;

public class SmsProcessWorker extends Worker {

    public SmsProcessWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String body = getInputData().getString("BODY");

        if (body == null) return Result.success();

        ParsedData data = SmsParser.parseTransaction(body);
        if (data == null) {
            return Result.success(); // Not a transaction
        }

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        
        List<Category> allCategories = db.categoryDao().getAllCategoriesSync();
        Integer categoryId = TransactionClassifier.classify(data.merchant, allCategories);

        Transaction tx = new Transaction();
        tx.amount = data.amount;
        tx.type = data.type;
        tx.date = System.currentTimeMillis();
        tx.merchantName = data.merchant;
        tx.referenceId = data.referenceId;
        tx.paymentMethod = data.paymentMethod;
        tx.categoryId = categoryId;

        try {
            long id = db.transactionDao().insert(tx);
            Log.d("SmsWorker", "Inserted TX with ID: " + id);
        } catch (Exception e) {
            Log.e("SmsWorker", "Error inserting transaction", e);
            return Result.failure();
        }

        return Result.success();
    }
}

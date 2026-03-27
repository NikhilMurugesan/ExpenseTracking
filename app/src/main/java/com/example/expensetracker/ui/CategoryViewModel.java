package com.example.expensetracker.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensetracker.data.AppDatabase;
import com.example.expensetracker.data.entity.Category;
import com.example.expensetracker.data.entity.CategorySum;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private LiveData<List<Category>> allCategories;
    private LiveData<List<CategorySum>> categorySums;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        allCategories = db.categoryDao().getAllCategories();
        categorySums = db.transactionDao().getCategorySums();
    }

    public LiveData<List<Category>> getAllCategories() { return allCategories; }
    public LiveData<List<CategorySum>> getCategorySums() { return categorySums; }
}

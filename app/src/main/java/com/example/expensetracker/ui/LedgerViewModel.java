package com.example.expensetracker.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensetracker.data.AppDatabase;
import com.example.expensetracker.data.entity.ContactLedger;

import java.util.List;

public class LedgerViewModel extends AndroidViewModel {
    private LiveData<List<ContactLedger>> allLedgers;

    public LedgerViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        allLedgers = db.contactLedgerDao().getAllLedgers();
    }

    public LiveData<List<ContactLedger>> getAllLedgers() {
        return allLedgers;
    }
}

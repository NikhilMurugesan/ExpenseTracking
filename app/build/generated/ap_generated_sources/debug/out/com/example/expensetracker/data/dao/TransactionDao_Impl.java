package com.example.expensetracker.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.expensetracker.data.entity.CategorySum;
import com.example.expensetracker.data.entity.Transaction;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `transactions` (`id`,`amount`,`type`,`date`,`merchantName`,`paymentMethod`,`referenceId`,`categoryId`,`contactId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Transaction entity) {
        statement.bindLong(1, entity.id);
        statement.bindDouble(2, entity.amount);
        if (entity.type == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.type);
        }
        statement.bindLong(4, entity.date);
        if (entity.merchantName == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.merchantName);
        }
        if (entity.paymentMethod == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.paymentMethod);
        }
        if (entity.referenceId == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.referenceId);
        }
        if (entity.categoryId == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.categoryId);
        }
        if (entity.contactId == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.contactId);
        }
      }
    };
  }

  @Override
  public long insert(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfTransaction.insertAndReturnId(transaction);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Transaction>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfReferenceId = CursorUtil.getColumnIndexOrThrow(_cursor, "referenceId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfContactId = CursorUtil.getColumnIndexOrThrow(_cursor, "contactId");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            _item = new Transaction();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.amount = _cursor.getDouble(_cursorIndexOfAmount);
            if (_cursor.isNull(_cursorIndexOfType)) {
              _item.type = null;
            } else {
              _item.type = _cursor.getString(_cursorIndexOfType);
            }
            _item.date = _cursor.getLong(_cursorIndexOfDate);
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _item.merchantName = null;
            } else {
              _item.merchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
              _item.paymentMethod = null;
            } else {
              _item.paymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            }
            if (_cursor.isNull(_cursorIndexOfReferenceId)) {
              _item.referenceId = null;
            } else {
              _item.referenceId = _cursor.getString(_cursorIndexOfReferenceId);
            }
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _item.categoryId = null;
            } else {
              _item.categoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            }
            if (_cursor.isNull(_cursorIndexOfContactId)) {
              _item.contactId = null;
            } else {
              _item.contactId = _cursor.getInt(_cursorIndexOfContactId);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getExpensesSince(final long startDate) {
    final String _sql = "SELECT SUM(amount) FROM transactions WHERE type = 'DEBIT' AND date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Transaction> getAllSynchronous() {
    final String _sql = "SELECT * FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
      final int _cursorIndexOfReferenceId = CursorUtil.getColumnIndexOrThrow(_cursor, "referenceId");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final int _cursorIndexOfContactId = CursorUtil.getColumnIndexOrThrow(_cursor, "contactId");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Transaction _item;
        _item = new Transaction();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.amount = _cursor.getDouble(_cursorIndexOfAmount);
        if (_cursor.isNull(_cursorIndexOfType)) {
          _item.type = null;
        } else {
          _item.type = _cursor.getString(_cursorIndexOfType);
        }
        _item.date = _cursor.getLong(_cursorIndexOfDate);
        if (_cursor.isNull(_cursorIndexOfMerchantName)) {
          _item.merchantName = null;
        } else {
          _item.merchantName = _cursor.getString(_cursorIndexOfMerchantName);
        }
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _item.paymentMethod = null;
        } else {
          _item.paymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        if (_cursor.isNull(_cursorIndexOfReferenceId)) {
          _item.referenceId = null;
        } else {
          _item.referenceId = _cursor.getString(_cursorIndexOfReferenceId);
        }
        if (_cursor.isNull(_cursorIndexOfCategoryId)) {
          _item.categoryId = null;
        } else {
          _item.categoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        }
        if (_cursor.isNull(_cursorIndexOfContactId)) {
          _item.contactId = null;
        } else {
          _item.contactId = _cursor.getInt(_cursorIndexOfContactId);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<CategorySum>> getCategorySums() {
    final String _sql = "SELECT categoryId, SUM(amount) as total FROM transactions WHERE type = 'DEBIT' GROUP BY categoryId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<CategorySum>>() {
      @Override
      @Nullable
      public List<CategorySum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategoryId = 0;
          final int _cursorIndexOfTotal = 1;
          final List<CategorySum> _result = new ArrayList<CategorySum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategorySum _item;
            _item = new CategorySum();
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _item.categoryId = null;
            } else {
              _item.categoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            }
            _item.total = _cursor.getDouble(_cursorIndexOfTotal);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

package com.example.expensetracker.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.expensetracker.data.entity.ContactLedger;
import java.lang.Class;
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
public final class ContactLedgerDao_Impl implements ContactLedgerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ContactLedger> __insertionAdapterOfContactLedger;

  private final EntityDeletionOrUpdateAdapter<ContactLedger> __updateAdapterOfContactLedger;

  public ContactLedgerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContactLedger = new EntityInsertionAdapter<ContactLedger>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `contact_ledger` (`id`,`name`,`phoneNumber`,`upiId`,`totalGiven`,`totalReceived`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ContactLedger entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.phoneNumber == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.phoneNumber);
        }
        if (entity.upiId == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.upiId);
        }
        statement.bindDouble(5, entity.totalGiven);
        statement.bindDouble(6, entity.totalReceived);
      }
    };
    this.__updateAdapterOfContactLedger = new EntityDeletionOrUpdateAdapter<ContactLedger>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `contact_ledger` SET `id` = ?,`name` = ?,`phoneNumber` = ?,`upiId` = ?,`totalGiven` = ?,`totalReceived` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ContactLedger entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.phoneNumber == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.phoneNumber);
        }
        if (entity.upiId == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.upiId);
        }
        statement.bindDouble(5, entity.totalGiven);
        statement.bindDouble(6, entity.totalReceived);
        statement.bindLong(7, entity.id);
      }
    };
  }

  @Override
  public long insert(final ContactLedger ledger) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfContactLedger.insertAndReturnId(ledger);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ContactLedger ledger) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContactLedger.handle(ledger);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public ContactLedger getByUpiId(final String upiId) {
    final String _sql = "SELECT * FROM contact_ledger WHERE upiId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (upiId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, upiId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
      final int _cursorIndexOfUpiId = CursorUtil.getColumnIndexOrThrow(_cursor, "upiId");
      final int _cursorIndexOfTotalGiven = CursorUtil.getColumnIndexOrThrow(_cursor, "totalGiven");
      final int _cursorIndexOfTotalReceived = CursorUtil.getColumnIndexOrThrow(_cursor, "totalReceived");
      final ContactLedger _result;
      if (_cursor.moveToFirst()) {
        _result = new ContactLedger();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _result.name = null;
        } else {
          _result.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
          _result.phoneNumber = null;
        } else {
          _result.phoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        }
        if (_cursor.isNull(_cursorIndexOfUpiId)) {
          _result.upiId = null;
        } else {
          _result.upiId = _cursor.getString(_cursorIndexOfUpiId);
        }
        _result.totalGiven = _cursor.getDouble(_cursorIndexOfTotalGiven);
        _result.totalReceived = _cursor.getDouble(_cursorIndexOfTotalReceived);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<ContactLedger>> getAllLedgers() {
    final String _sql = "SELECT * FROM contact_ledger";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"contact_ledger"}, false, new Callable<List<ContactLedger>>() {
      @Override
      @Nullable
      public List<ContactLedger> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfUpiId = CursorUtil.getColumnIndexOrThrow(_cursor, "upiId");
          final int _cursorIndexOfTotalGiven = CursorUtil.getColumnIndexOrThrow(_cursor, "totalGiven");
          final int _cursorIndexOfTotalReceived = CursorUtil.getColumnIndexOrThrow(_cursor, "totalReceived");
          final List<ContactLedger> _result = new ArrayList<ContactLedger>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ContactLedger _item;
            _item = new ContactLedger();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _item.name = null;
            } else {
              _item.name = _cursor.getString(_cursorIndexOfName);
            }
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _item.phoneNumber = null;
            } else {
              _item.phoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            if (_cursor.isNull(_cursorIndexOfUpiId)) {
              _item.upiId = null;
            } else {
              _item.upiId = _cursor.getString(_cursorIndexOfUpiId);
            }
            _item.totalGiven = _cursor.getDouble(_cursorIndexOfTotalGiven);
            _item.totalReceived = _cursor.getDouble(_cursorIndexOfTotalReceived);
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

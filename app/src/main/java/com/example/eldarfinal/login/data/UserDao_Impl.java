package com.example.eldarfinal.login.data;

import android.database.Cursor;
import android.os.CancellationSignal;

import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.example.eldarfinal.login.data.UserDao;
import com.example.eldarfinal.login.data.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
    private final RoomDatabase __db;

    private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

    public UserDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
            @Override
            public String createQuery() {
                return "INSERT OR ABORT INTO `users` (`id`,`fullName`,`dni`) VALUES (nullif(?, 0),?,?)";
            }

            @Override
            public void bind(SupportSQLiteStatement stmt, UserEntity value) {
                stmt.bindLong(1, value.getId());
                if (value.getFullName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getFullName());
                }
                if (value.getDni() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getDni());
                }
            }
        };
    }

    @Override
    public Object insertUser(final UserEntity user, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
            @Override
            public Unit call() throws Exception {
                __db.beginTransaction();
                try {
                    __insertionAdapterOfUserEntity.insert(user);
                    __db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    __db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override
    public Object getAllUsers(final Continuation<? super List<UserEntity>> continuation) {
        final String _sql = "SELECT * FROM users";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
        final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserEntity>>() {
            @Override
            public List<UserEntity> call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
                    final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
                    final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
                    while(_cursor.moveToNext()) {
                        final UserEntity _item;
                        final int _tmpId;
                        _tmpId = _cursor.getInt(_cursorIndexOfId);
                        final String _tmpFullName;
                        if (_cursor.isNull(_cursorIndexOfFullName)) {
                            _tmpFullName = null;
                        } else {
                            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
                        }
                        final String _tmpDni;
                        if (_cursor.isNull(_cursorIndexOfDni)) {
                            _tmpDni = null;
                        } else {
                            _tmpDni = _cursor.getString(_cursorIndexOfDni);
                        }
                        _item = new UserEntity(_tmpId,_tmpFullName,_tmpDni);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, continuation);
    }

    @Override
    public Object getUserByFullNameAndDni(final String fullName, final String dni,
                                          final Continuation<? super UserEntity> continuation) {
        final String _sql = "SELECT * FROM users WHERE fullName = ? AND dni = ?";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
        int _argIndex = 1;
        if (fullName == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, fullName);
        }
        _argIndex = 2;
        if (dni == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, dni);
        }
        final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
            @Override
            public UserEntity call() throws Exception {
                final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
                try {
                    final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
                    final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
                    final UserEntity _result;
                    if(_cursor.moveToFirst()) {
                        final int _tmpId;
                        _tmpId = _cursor.getInt(_cursorIndexOfId);
                        final String _tmpFullName;
                        if (_cursor.isNull(_cursorIndexOfFullName)) {
                            _tmpFullName = null;
                        } else {
                            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
                        }
                        final String _tmpDni;
                        if (_cursor.isNull(_cursorIndexOfDni)) {
                            _tmpDni = null;
                        } else {
                            _tmpDni = _cursor.getString(_cursorIndexOfDni);
                        }
                        _result = new UserEntity(_tmpId,_tmpFullName,_tmpDni);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, continuation);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}